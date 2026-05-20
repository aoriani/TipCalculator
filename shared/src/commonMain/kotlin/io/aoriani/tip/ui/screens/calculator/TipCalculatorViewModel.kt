package io.aoriani.tip.ui.screens.calculator

import androidx.lifecycle.ViewModel
import io.aoriani.tip.DollarAmount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class TipUiState(
    val amount: String = "",
    val selectedPercentage: Percentage = Percentage.Fifteen,
) {
    enum class Percentage(val value: DollarAmount) {
        Fifteen(value = DollarAmount("1.15")),
        Eighteen(value = DollarAmount("1.18")),
        Twenty(value = DollarAmount("1.20"),),
        TwentyFive(value = DollarAmount("1.25"))
    }

    val total: DollarAmount = amount.takeIf { it.matches(COMPLETE_DOLLAR_AMOUNT_REGEX) }
        ?.let { DollarAmount(it) * selectedPercentage.value }
        ?: DollarAmount("0.00")
}

sealed interface TipUiEvent {
    class NewAmountEvent(val amount: String) : TipUiEvent
    class NewPercentage(val percentage: TipUiState.Percentage) : TipUiEvent
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