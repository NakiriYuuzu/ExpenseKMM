package net.yuuzu.expanseapp.core.presentation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import net.yuuzu.expanseapp.ui.theme.DarkColors
import net.yuuzu.expanseapp.ui.theme.LightColors

@Composable
actual fun ExpenseTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if(darkTheme) DarkColors else LightColors,
        typography = net.yuuzu.expanseapp.ui.theme.typography,
        content = content
    )
}