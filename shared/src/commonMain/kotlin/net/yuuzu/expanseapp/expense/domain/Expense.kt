package net.yuuzu.expanseapp.expense.domain

data class Expense(
    val id: Long?,
    val title: String,
    val description: String,
    val amount: Int,
    val category: String,
    val timestamp: Long,
)
