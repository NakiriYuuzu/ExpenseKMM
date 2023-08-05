package net.yuuzu.expanseapp.expense.presentation.main_screen

sealed interface ExpenseEvent {
    object OnSettingsSaveClick: ExpenseEvent
    data class OnNameChanged(val value: String): ExpenseEvent
    data class OnBudgetChanged(val value: String): ExpenseEvent
}