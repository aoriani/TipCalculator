package io.aoriani.tip.ui.screens.calculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import io.aoriani.tip.ui.screens.calculator.TipUiState
import io.aoriani.tip.ui.theme.Dimens
import tipcalculator.shared.generated.resources.Res
import tipcalculator.shared.generated.resources.eighteen_percent
import tipcalculator.shared.generated.resources.fifteen_percent
import tipcalculator.shared.generated.resources.twenty_five_percent
import tipcalculator.shared.generated.resources.twenty_percent

@Composable
@ReadOnlyComposable
fun TipUiState.Percentage.toStringResource() = when (this) {
    TipUiState.Percentage.Fifteen -> Res.string.fifteen_percent
    TipUiState.Percentage.Eighteen -> Res.string.eighteen_percent
    TipUiState.Percentage.Twenty -> Res.string.twenty_percent
    TipUiState.Percentage.TwentyFive -> Res.string.twenty_five_percent
}

@Composable
fun TipPercentageChips(
    percentages: List<Int>,
    selectedPercentage: Int,
    onPercentageSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.SpacingMedium)
    ) {
        percentages.forEach { percentage ->
            val isSelected = percentage == selectedPercentage
            val backgroundColor = if (isSelected) {
                MaterialTheme.colorScheme.secondary
            } else {
                MaterialTheme.colorScheme.surfaceContainer
            }
            val contentColor = if (isSelected) {
                MaterialTheme.colorScheme.onSecondary
            } else {
                MaterialTheme.colorScheme.onSurface
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(Dimens.ChipHeight)
                    .clip(RoundedCornerShape(Dimens.CornerRadiusLarge))
                    .background(backgroundColor)
                    .clickable { onPercentageSelected(percentage) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$percentage%",
                    style = MaterialTheme.typography.headlineMedium,
                    color = contentColor
                )
            }
        }
    }
}
