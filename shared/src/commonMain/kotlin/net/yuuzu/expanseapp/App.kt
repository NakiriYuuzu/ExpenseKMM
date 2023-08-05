package net.yuuzu.expanseapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import net.yuuzu.expanseapp.core.presentation.ExpenseTheme
import net.yuuzu.expanseapp.expense.presentation.detail_screen.DetailScreen
import net.yuuzu.expanseapp.expense.presentation.main_screen.ExpenseScreen

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    ExpenseTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
//            ExpenseScreen()
            DetailScreen()
        }
    }
}