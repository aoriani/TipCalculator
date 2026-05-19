package io.aoriani.tip.ui.screens.calculator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import io.aoriani.tip.DollarAmount
import io.aoriani.tip.ui.theme.BillAmountInput
import io.aoriani.tip.ui.theme.ResultCard
import io.aoriani.tip.ui.theme.TipPercentageChips
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
                navigationIcon = {
                    IconButton(onClick = {}) {
                        // Placeholder for Calculator Icon
                        Box(modifier = Modifier.size(24.dp).padding(4.dp)) {
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colorScheme.onSecondary,
                                shape = androidx.compose.foundation.shape.CircleShape
                            ) {}
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        // Placeholder for Settings Icon
                        Box(modifier = Modifier.size(24.dp).padding(4.dp)) {
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colorScheme.onSecondary,
                                shape = androidx.compose.foundation.shape.CircleShape
                            ) {}
                        }
                    }
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
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            BillAmountInput(
                value = billAmount,
                onValueChange = { billAmount = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            TipPercentageChips(
                percentages = listOf(15, 18, 20, 25),
                selectedPercentage = selectedPercentage,
                onPercentageSelected = { selectedPercentage = it }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
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
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
