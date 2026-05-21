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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import io.aoriani.tip.ui.screens.calculator.components.BillAmountInput
import io.aoriani.tip.ui.screens.calculator.components.ResultCard
import io.aoriani.tip.ui.screens.calculator.components.TipPercentageChips
import io.aoriani.tip.ui.screens.calculator.components.toStringResource
import io.aoriani.tip.ui.theme.Dimens
import io.aoriani.tip.ui.theme.TipTheme
import org.jetbrains.compose.resources.stringResource
import tipcalculator.shared.generated.resources.Res
import tipcalculator.shared.generated.resources.chip_tip_percentage
import tipcalculator.shared.generated.resources.result_total
import tipcalculator.shared.generated.resources.screen_title

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
fun TipCalculatorScreen(
    state: TipUiState = TipUiState(),
    onEvent: (TipUiEvent) -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(Res.string.screen_title),
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
                value = state.amount,
                onValueChange = { newAmount -> onEvent(TipUiEvent.NewAmountEvent(newAmount)) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(Dimens.SpacingXLarge))

            TipPercentageChips(
                selectedPercentage = state.selectedPercentage,
                onPercentageSelected = { newPercentage ->
                    onEvent(
                        TipUiEvent.NewPercentage(
                            newPercentage
                        )
                    )
                }
            )

            Spacer(modifier = Modifier.height(Dimens.SpacingXLarge))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingNormal)
            ) {
                ResultCard(
                    label = stringResource(
                        Res.string.chip_tip_percentage,
                        stringResource(state.selectedPercentage.toStringResource())
                    ),
                    amount = state.tipValue.toString(),
                    modifier = Modifier.weight(1f),
                    isPrimary = true
                )
                ResultCard(
                    label = stringResource(Res.string.result_total),
                    amount = state.totalValue.toString(),
                    modifier = Modifier.weight(1f),
                    isPrimary = false
                )
            }

            Spacer(modifier = Modifier.height(Dimens.SpacingXLarge))
        }
    }
}
