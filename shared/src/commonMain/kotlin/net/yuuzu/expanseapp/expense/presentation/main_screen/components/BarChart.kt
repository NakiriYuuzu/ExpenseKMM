package net.yuuzu.expanseapp.expense.presentation.main_screen.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import net.yuuzu.expanseapp.expense.domain.CategoryColor

@ExperimentalAnimationApi
@Composable
fun BarChart(
    data: Map<String, Double>,
    offer: Double,
    categoryColor: List<CategoryColor> = emptyList(),
    barCornersRadius: Float = 25f,
    barGradientColors: List<Color> = listOf(
        Color(0xFF40C7D7),
        Color(0xff81deea),
        Color(0xFF40C7D7),
        Color(0xff81deea)
    ),
    barWarningsGradientColors: List<Color> = listOf(
        Color(0xFFE06C83),
        Color(0xFFE74061),
        Color(0xFFE06C83),
        Color(0xFFE74061)
    ),
    barWidth: Float = 50f,
    height: Dp,
    title: String = "Bar Chart",
    labelOffset: Float = 60f,
    labelColor: Color = MaterialTheme.colorScheme.onSurface,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    topStartRadius: Dp = 25.dp,
    topEndRadius: Dp = 25.dp,
    bottomStartRadius: Dp = 0.dp,
    bottomEndRadius: Dp = 0.dp,
    isExpanded: Boolean = true,
    closeIcon: ImageVector = Icons.Default.KeyboardArrowUp,
    onCloseListener: () -> Unit
) {
    val shape = RoundedCornerShape(
        topStart = topStartRadius,
        topEnd = topEndRadius,
        bottomEnd = bottomEndRadius,
        bottomStart = bottomStartRadius
    )

    var screenSize by remember {
        mutableStateOf(Size.Zero)
    }

    var chosenBar by remember {
        mutableStateOf(-1)
    }
    var chosenBarKey by remember {
        mutableStateOf("")
    }

    val cardHeight by animateDpAsState(
        targetValue = if (isExpanded) height else 50.dp,
        animationSpec = tween(
            1000,
            easing = LinearOutSlowInEasing
        )
    )

    val rotate by animateFloatAsState(
        targetValue = if (isExpanded) 0f else 180f,
        animationSpec = tween(
            700,
            easing = LinearOutSlowInEasing
        )
    )

    LaunchedEffect(chosenBar) {
        delay(3000)
        chosenBarKey = ""
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .shadow(0.dp, shape = shape)
            .clip(shape = shape)
            .background(
                color = backgroundColor,
                shape = shape
            )
            .animateContentSize()
    ) {
        IconButton(
            onClick = onCloseListener,
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = closeIcon,
                contentDescription = "Close chart",
                modifier = Modifier.rotate(rotate)
            )
        }

        Text(
            text = title,
            color = labelColor,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 20.dp, top = 10.dp)
        )

        Canvas(modifier = Modifier
            .fillMaxSize()
            .alpha(if (cardHeight < height) (cardHeight - 90.dp) / height else 1f)
            .padding(
                top = 65.dp,
                bottom = 20.dp,
                start = 30.dp,
                end = 30.dp
            )
            .pointerInput(Unit) {
                this.detectTapGestures(onPress = {
                    chosenBar = detectPosition(
                        screenSize = screenSize,
                        offset = it,
                        listSize = data.size,
                        itemWidth = barWidth
                    )
                    if (chosenBar >= 0) {
                        chosenBarKey = data.toList()[chosenBar].first
                    }
                })
            },
            onDraw = {
                screenSize = size
                val spaceBetweenBars =
                    (size.width - (data.size * barWidth)) / (data.size - 1)
                val maxBarHeight = data.values.maxOf { it }
                val barScale = size.height / maxBarHeight

                var spaceStep = 0f

                for (item in data) {
                    val topLeft = Offset(
                        x = spaceStep,
                        y = (size.height - item.value * barScale - labelOffset).toFloat()
                    )

                    var barWarningsColor = barWarningsGradientColors

                    if (categoryColor.isNotEmpty()) {
                        barWarningsColor = listOf(
                            convertToColor(categoryColor.find { it.category == item.key }?.color),
                            convertToColor(categoryColor.find { it.category == item.key }?.color)
                        )
                    }

                    val currentBarColor = if (item.value >= offer) barWarningsColor else barGradientColors

                    //--------------------(draw bars)--------------------//
                    drawRoundRect(
                        brush = Brush.linearGradient(
                            colors = currentBarColor
                        ),
                        topLeft = topLeft,
                        size = Size(
                            width = barWidth,
                            height = size.height - topLeft.y - labelOffset
                        ),
                        cornerRadius = CornerRadius(barCornersRadius, barCornersRadius)
                    )

                    spaceStep += spaceBetweenBars + barWidth
                }
            })
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = (screenSize.width / (barWidth * 2)).dp)
                .align(Alignment.BottomCenter)
                .matchParentSize()
                .alpha(if (isExpanded) 1f else 0f)
        ) {
            println("Yuuzu: ${(screenSize.width / (barWidth * 2))}")
            data.forEach {
                Text(
                    text = it.key.take(3),
                    color = labelColor,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .width(50.dp)
                )
            }
        }
    }
}


private fun detectPosition(screenSize: Size, offset: Offset, listSize: Int, itemWidth: Float): Int {
    val spaceBetweenBars =
        (screenSize.width - (listSize * itemWidth)) / (listSize - 1)
    var spaceStep = 0f
    for (i in 0 until listSize) {
        if (offset.x in spaceStep..(spaceStep + itemWidth)) {
            return i
        }
        spaceStep += spaceBetweenBars + itemWidth
    }
    return -1
}

private fun convertToColor(strColor: String?): Color {
    if (strColor == null) return net.yuuzu.expanseapp.ui.theme.seed
    return net.yuuzu.expanseapp.ui.theme.md_theme_dark_scrim
}