package net.yuuzu.expanseapp.expense.presentation.main_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import net.yuuzu.expanseapp.expense.domain.DataUser
import net.yuuzu.expanseapp.expense.domain.ExpenseDataSource
import net.yuuzu.expanseapp.expense.domain.ExpenseValidator
import net.yuuzu.expanseapp.util.DateTimeCalculation

class ExpenseViewModel(
    private val dataSource: ExpenseDataSource
): ViewModel() {

    private val _state = MutableStateFlow(ExpenseState())
    val state = _state.asStateFlow()

    var userData: DataUser? by mutableStateOf(null)
        private set

    private var getExpensesJob: Job? = null

    init {
        getWeeklyExpenses()
        getMonthlyExpenses()
    }

    private fun getWeeklyExpenses() {
        getExpensesJob?.cancel()
        getExpensesJob = dataSource.getExpensesByDate(DateTimeCalculation.firstDayOfWeekEpoch, DateTimeCalculation.lastDayOfWeekEpoch)
            .onEach { expenses ->
                _state.value = _state.value.copy(
                    expenses = expenses
                )
            }
            .launchIn(viewModelScope)
    }

    private fun getMonthlyExpenses() {
        getExpensesJob?.cancel()
        getExpensesJob = dataSource.getExpensesByDate(DateTimeCalculation.firstDayOfMonthEpoch, DateTimeCalculation.lastDayOfMonthEpoch)
            .onEach { expenses ->
                _state.value = _state.value.copy(
                    expenses = expenses
                )
            }
            .launchIn(viewModelScope)
    }

    fun onEvent(event: ExpenseEvent) {
        when (event) {
            ExpenseEvent.OnSettingsSaveClick -> {
                userData?.let { dataUser ->
                    val result = ExpenseValidator.validateUserData(dataUser)
                    val errors = listOfNotNull(
                        result.name,
                        result.budget
                    )

                    if (errors.isEmpty()) {
                        // TODO: save user data into shared preferences

                    } else {
                        _state.update { it.copy(
                            onNameError = result.name,
                            budgetError = result.budget
                        ) }
                    }
                }
            }
            is ExpenseEvent.OnNameChanged -> {
                userData = userData?.copy(
                    name = event.value
                )
            }
            is ExpenseEvent.OnBudgetChanged -> {
                userData = userData?.copy(
                    budget = event.value.toInt()
                )
            }
        }
    }
}