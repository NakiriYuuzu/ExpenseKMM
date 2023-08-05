package net.yuuzu.expanseapp.core.presentation

import androidx.compose.runtime.Composable

@Composable
expect fun ExpenseTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)