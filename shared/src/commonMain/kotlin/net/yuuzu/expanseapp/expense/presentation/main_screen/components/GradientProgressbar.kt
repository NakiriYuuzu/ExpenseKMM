package net.yuuzu.expanseapp.expense.presentation.main_screen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun GradientProgressbar(
    indicatorHeight: Dp = 20.dp,
    backgroundIndicatorColor: Color = Color.LightGray.copy(alpha = 0.75f),
    indicatorPadding: Dp = 16.dp,
    gradientColors: List<Color> = listOf(
        Color(0xFF40C7D7),
        Color(0xFF40C7D7),
        Color(0xff81deea),
        Color(0xff81deea)
    ),
    warningGradientColor: List<Color> = listOf(
        Color(0xFFE06C83),
        Color(0xFFE74061),
        Color(0xFFE06C83),
        Color(0xFFE74061)
    ),
    animationDuration: Int = 1000,
    animationDelay: Int = 0,
    remainingCost: Int,
    budgetCost: Int,
) {

    val calculateValue = if (remainingCost > budgetCost) 100f
                    else if (remainingCost <= 0) 0f
                    else (remainingCost.toFloat() / budgetCost.toFloat()) * 100f

    val animateNumber = animateFloatAsState(
        targetValue = calculateValue,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        )
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(indicatorHeight)
            .padding(start = indicatorPadding, end = indicatorPadding)
    ) {

        // Background indicator
        drawLine(
            color = backgroundIndicatorColor,
            cap = StrokeCap.Round,
            strokeWidth = size.height,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = size.width, y = 0f)
        )

        // Convert the downloaded percentage into progress (width of foreground indicator)
        val progress = (animateNumber.value / 100) * size.width
        val color = if (calculateValue < 30) warningGradientColor else gradientColors

        // Foreground indicator
        drawLine(
            brush = Brush.linearGradient(
                colors = color
            ),
            cap = StrokeCap.Round,
            strokeWidth = size.height,
            start = Offset(x = 0f, y = 0f),
            end = Offset(x = progress, y = 0f)
        )
    }
}
