package io.aoriani.tip

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.aoriani.tip.ui.screens.calculator.TipCalculatorScreen
import io.aoriani.tip.ui.theme.TipTheme

@Composable
@Preview
fun App() {
    TipTheme {
        TipCalculatorScreen()
    }
}
