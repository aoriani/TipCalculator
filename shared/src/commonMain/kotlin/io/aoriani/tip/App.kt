package io.aoriani.tip

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import io.aoriani.tip.ui.screens.calculator.TipCalculatorScreen
import io.aoriani.tip.ui.screens.calculator.TipCalculatorViewModel
import io.aoriani.tip.ui.theme.TipTheme

@Composable
@Preview
fun App() {
    TipTheme {
        val viewModel = viewModel { TipCalculatorViewModel() }
        val state by viewModel.state.collectAsStateWithLifecycle()
        val onEvent by rememberUpdatedState(viewModel::onEvent)
        TipCalculatorScreen(
            state = state,
            onEvent = onEvent
        )
    }
}
