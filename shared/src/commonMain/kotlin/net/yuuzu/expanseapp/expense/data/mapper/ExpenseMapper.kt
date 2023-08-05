package net.yuuzu.expanseapp.expense.data.mapper

import database.ExpenseEntity
import net.yuuzu.expanseapp.expense.domain.Expense

// Database
fun ExpenseEntity.toExpense(): Expense {
    return Expense(
        id = id,
        title = title,
        description = description,
        amount = amount.toInt(),
        category = category,
        timestamp = timestamp
    )
}