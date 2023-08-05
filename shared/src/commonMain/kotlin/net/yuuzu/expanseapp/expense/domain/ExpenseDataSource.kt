package net.yuuzu.expanseapp.expense.domain

import kotlinx.coroutines.flow.Flow

interface ExpenseDataSource {
    fun getExpenses(): Flow<List<Expense>>
    fun getExpensesByDate(start: Long, end: Long): Flow<List<Expense>>
    suspend fun insertExpense(expense: Expense)
    suspend fun deleteExpense(id: Long)
}