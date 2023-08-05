package net.yuuzu.expanseapp.expense.data.local

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import net.yuuzu.expanseapp.data.ExpenseDatabase
import net.yuuzu.expanseapp.expense.data.mapper.toExpense
import net.yuuzu.expanseapp.expense.domain.Expense
import net.yuuzu.expanseapp.expense.domain.ExpenseDataSource

class ExpenseDataSourceImpl(
    db: ExpenseDatabase
): ExpenseDataSource {

    private val queries = db.expenseQueries

    override fun getExpenses(): Flow<List<Expense>> {
        return queries
            .getExpenses()
            .asFlow()
            .mapToList()
            .map { expenseEntities ->
                expenseEntities.map {
                    it.toExpense()
                }
            }
    }

    override fun getExpensesByDate(start: Long, end: Long): Flow<List<Expense>> {
        return queries
            .getExpensesByDate(start, end)
            .asFlow()
            .mapToList()
            .map { expenseEntities ->
                expenseEntities.map {
                    it.toExpense()
                }
            }
    }

    override suspend fun insertExpense(expense: Expense) {
        queries.insertExpenseEntity(
            id = expense.id,
            title = expense.title,
            description = expense.description,
            amount = "${expense.amount}",
            category = expense.category,
            timestamp = Clock.System.now()
                .toLocalDateTime(TimeZone.currentSystemDefault())
                .toInstant(TimeZone.currentSystemDefault())
                .toEpochMilliseconds()
        )
    }

    override suspend fun deleteExpense(id: Long) {
        queries.deleteExpense(id)
    }
}