package net.yuuzu.expanseapp.expense.domain

object ExpenseValidator {
    fun validateExpense(expense: Expense): ValidationExpenseResult {
        var result = ValidationExpenseResult()

        if(expense.title.isBlank()) {
            result = result.copy(title = "The title can't be empty.")
        }

        return result
    }

    fun validateUserData(userData: DataUser): ValidationUserDataResult {
        var result = ValidationUserDataResult()

        if(userData.name.isBlank()) {
            result = result.copy(name = "The name can't be empty.")
        }

        if (userData.budget <= 0) {
            result = result.copy(budget = "The budget can't be zero.")
        }

        return result
    }

    data class ValidationExpenseResult(
        val title: String? = null,
    )

    data class ValidationUserDataResult(
        val name: String? = null,
        val budget: String? = null,
    )
}