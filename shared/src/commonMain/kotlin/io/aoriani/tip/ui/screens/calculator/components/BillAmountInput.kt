package io.aoriani.tip.ui.screens.calculator.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import io.aoriani.tip.ui.theme.Dimens
import io.aoriani.tip.ui.theme.NumberXl
import org.jetbrains.compose.resources.stringResource
import tipcalculator.shared.generated.resources.Res
import tipcalculator.shared.generated.resources.bill_amount

@Composable
fun BillAmountInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(Res.string.bill_amount),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = Dimens.SpacingSmall)
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = Dimens.BorderWidthThin,
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape = RoundedCornerShape(Dimens.CornerRadiusSmall)
                ),
            shape = RoundedCornerShape(Dimens.CornerRadiusSmall),
            color = MaterialTheme.colorScheme.surfaceContainerLowest
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = Dimens.SpacingLarge, vertical = Dimens.SpacingXLarge),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$",
                    style = MaterialTheme.typography.displayLarge.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                    ),
                    modifier = Modifier.padding(end = Dimens.SpacingNormal)
                )
                BasicTextField(
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = NumberXl.copy(color = MaterialTheme.colorScheme.onSurface),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
