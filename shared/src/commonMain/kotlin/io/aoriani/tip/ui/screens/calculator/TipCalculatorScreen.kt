package io.aoriani.tip.ui.screens.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import io.aoriani.tip.DollarAmount
import io.aoriani.tip.ui.screens.calculator.components.BillAmountInput
import io.aoriani.tip.ui.screens.calculator.components.ResultCard
import io.aoriani.tip.ui.screens.calculator.components.TipPercentageChips
import io.aoriani.tip.ui.theme.Dimens
import io.aoriani.tip.ui.theme.TipTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TipCalculatorScreenPreview() {
    TipTheme {
        TipCalculatorScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipCalculatorScreen() {
    var billAmount by remember { mutableStateOf("") }
    var selectedPercentage by remember { mutableStateOf(20) }

    val billValue = try { DollarAmount(billAmount.ifBlank { "0" }) } catch (e: Exception) { DollarAmount("0") }
    val tipRate = DollarAmount((selectedPercentage / 100.0).toString())
    val tipValue = billValue * tipRate
    val totalValue = billValue + tipValue

    val formattedTip = tipValue.toString()
    val formattedTotal = totalValue.toString()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Tip",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = Dimens.SpacingLarge)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(Dimens.SpacingXLarge))

            BillAmountInput(
                value = billAmount,
                onValueChange = { billAmount = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(Dimens.SpacingXLarge))

            TipPercentageChips(
                percentages = listOf(15, 18, 20, 25),
                selectedPercentage = selectedPercentage,
                onPercentageSelected = { selectedPercentage = it }
            )

            Spacer(modifier = Modifier.height(Dimens.SpacingXLarge))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingNormal)
            ) {
                ResultCard(
                    label = "TIP ($selectedPercentage%)",
                    amount = formattedTip,
                    modifier = Modifier.weight(1f),
                    isPrimary = true
                )
                ResultCard(
                    label = "TOTAL",
                    amount = formattedTotal,
                    modifier = Modifier.weight(1f),
                    isPrimary = false
                )
            }
            
            Spacer(modifier = Modifier.height(Dimens.SpacingXLarge))
        }
    }
}
