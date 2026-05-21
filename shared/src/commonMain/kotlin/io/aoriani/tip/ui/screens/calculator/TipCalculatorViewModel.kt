package io.aoriani.tip.ui.screens.calculator

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@Immutable
data class TipUiState(
    val amount: String = "",
    val selectedPercentage: Percentage = Percentage.Fifteen,
) {
    enum class Percentage(val value: Double) {
        Fifteen(value = 0.15),
        Eighteen(0.18),
        Twenty(0.20),
        TwentyFive(0.25)
    }

    private val baseAmount: Double? = amount
        .takeIf { it.matches(COMPLETE_DOLLAR_AMOUNT_REGEX) }
        ?.toDouble()

    @Stable
    val tipValue: Double = baseAmount?.let { it * selectedPercentage.value }
        ?: 0.0

    @Stable
    val totalValue: Double = baseAmount?.let { it + tipValue }
        ?: 0.0
}

sealed interface TipUiEvent {
    data class NewAmountEvent(val amount: String) : TipUiEvent
    data class NewPercentage(val percentage: TipUiState.Percentage) : TipUiEvent
}

private val COMPLETE_DOLLAR_AMOUNT_REGEX = Regex("""^-?\d+(\.\d{1,2})?$""")
private val EDITING_DOLLAR_AMOUNT_REGEX = Regex("""^-?\d+(\.\d{0,2})?$""")

class TipCalculatorViewModel : ViewModel() {
    val state: StateFlow<TipUiState>
        field = MutableStateFlow(TipUiState())

    fun onEvent(event: TipUiEvent) {
        state.update { oldState ->
            when (event) {
                is TipUiEvent.NewAmountEvent -> if (isValidEditing(event.amount)) {
                    oldState.copy(amount = event.amount)
                } else oldState

                is TipUiEvent.NewPercentage -> oldState.copy(
                    selectedPercentage = event.percentage
                )
            }
        }
    }

    private fun isValidEditing(value: String) = EDITING_DOLLAR_AMOUNT_REGEX.matches(value)
}