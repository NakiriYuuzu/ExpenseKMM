package net.yuuzu.expanseapp.expense.presentation.main_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import net.yuuzu.expanseapp.expense.domain.Expense
import net.yuuzu.expanseapp.expense.presentation.main_screen.components.BarChart
import net.yuuzu.expanseapp.expense.presentation.main_screen.components.CardOfSpendAndBudget
import net.yuuzu.expanseapp.expense.presentation.main_screen.components.ExpenseItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ExpenseScreen(

) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                containerColor = MaterialTheme.colorScheme.surfaceTint,
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add expense")
            }
        },
    ) {

        var showChart by remember { mutableStateOf(true) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .height(400.dp)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Hello, Yuuzu",
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium
                )

                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    modifier = Modifier
                        .clip(shape = MaterialTheme.shapes.large)
                        .clickable(
                            onClick = {

                            }
                        )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            CardOfSpendAndBudget(
                title = "Monthly Remaining Budget",
                remainingCost = 1000,
                budgetCost = 2000,
            )

            Spacer(modifier = Modifier.height(16.dp))

            BarChart(
                data = mapOf(
                    "Mon" to 100.0,
                    "Tue" to 200.0,
                    "Wed" to 300.0,
                    "Thu" to 400.0,
                    "Fri" to 500.0,
                    "Sat" to 600.0,
                    "Sun" to 700.0,
                ),
                offer = 1000.0,
                title = "Weekly Expenses",
                height = 250.dp,
                isExpanded = showChart,
                bottomEndRadius = 24.dp,
                bottomStartRadius = 24.dp,
            ) {
                showChart = !showChart
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Weekly Record",
                    style = MaterialTheme.typography.titleSmall,
                )

                Row(
                    modifier = Modifier
                        .clip(shape = MaterialTheme.shapes.large)
                        .clickable {
                            // TODO: Navigate to see all
                        },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "See All",
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 4.dp)
                    )

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "right arrow",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(start = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(10) {
                    ExpenseItem(
                        expense = Expense(
                            id = 1,
                            category = "Food",
                            amount = 100,
                            timestamp = 12313131,
                            title = "Food",
                            description = "Bought some food",
                        ),
                        itemColor = MaterialTheme.colorScheme.surfaceTint,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .clickable {
                                // TODO: Navigate to edit screen
//                                        navController.navigate(
//                                            Screen.AddEditScreen.route + "?expenseId=${expense.id}"
//                                        )
                            }
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}