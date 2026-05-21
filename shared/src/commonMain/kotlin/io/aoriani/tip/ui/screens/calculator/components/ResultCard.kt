package io.aoriani.tip.ui.screens.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import io.aoriani.tip.ui.theme.Dimens
import io.aoriani.tip.ui.theme.TipTheme
import org.jetbrains.compose.resources.stringResource
import tipcalculator.shared.generated.resources.Res
import tipcalculator.shared.generated.resources.amount

@Composable
fun ResultCard(
    label: String,
    amount: String,
    modifier: Modifier = Modifier,
    isPrimary: Boolean = false
) {
    val backgroundColor = if (isPrimary) {
        MaterialTheme.colorScheme.surfaceContainer
    } else {
        MaterialTheme.colorScheme.surfaceContainerLow
    }
    
    val amountColor = if (isPrimary) {
        MaterialTheme.colorScheme.secondary
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.ResultCardHeight),
        shape = RoundedCornerShape(Dimens.CornerRadiusNormal),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier.padding(Dimens.SpacingLarge),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = stringResource(Res.string.amount, amount),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = amountColor
            )
        }
    }
}

@Preview
@Composable
fun ResultCardPrimaryPreview() {
    TipTheme {
        ResultCard(
            label = "TIP (20%)",
            amount = "10.00",
            isPrimary = true
        )
    }
}

@Preview
@Composable
fun ResultCardSecondaryPreview() {
    TipTheme {
        ResultCard(
            label = "TOTAL",
            amount = "60.00",
            isPrimary = false
        )
    }
}
