package net.yuuzu.expanseapp.expense.presentation.main_screen

import net.yuuzu.expanseapp.expense.domain.Expense

data class ExpenseState(
    val expenses: List<Expense> = emptyList(),
    val weeklyExpenses: List<Expense> = emptyList(),
    val monthlyExpenses: List<Expense> = emptyList(),
    val name: String = "User",
    val budget: Int = 0,
    val onNameError: String? = null,
    val budgetError: String? = null
)