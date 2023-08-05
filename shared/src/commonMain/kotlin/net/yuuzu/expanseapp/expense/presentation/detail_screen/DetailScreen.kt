package net.yuuzu.expanseapp.expense.presentation.detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lt.compose_views.compose_pager.ComposePager
import com.lt.compose_views.compose_pager.ComposePagerState
import com.lt.compose_views.pager_indicator.TextPagerIndicator

@Composable
fun DetailScreen() {
    Column {
        MTextPagerIndicator()
        MComposePager()
    }
}

private val colors = mutableStateListOf(
    Color.Cyan to "status 01",
    Color.Magenta to "status 02",
    Color.Blue to "status 03",
)
private val texts = colors.map { it.second }
private val pagerState = ComposePagerState()
private val textColor = Color(0xff999999)
private val selectTextColor = Color(0xff333333)
private val selectIndicatorColor = Color(0xff1773FC)


@Composable
private fun MComposePager() {
    ComposePager(
        pageCount = colors.size,
        modifier = Modifier.fillMaxSize(),
        composePagerState = pagerState,
        orientation = Orientation.Horizontal,
    ) {
        Box(
            contentAlignment = androidx.compose.ui.Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(colors[index].first)
        ) {
            Text(
                text = colors[index].second,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

@Composable
private fun MTextPagerIndicator() {
    TextPagerIndicator(
        texts = texts,
        offsetPercentWithSelectFlow = remember { pagerState.createChildOffsetPercentFlow() },
        selectIndexFlow = remember { pagerState.createCurrSelectIndexFlow() },
        fontSize = 16.sp,
        selectFontSize = 20.sp,
        textColor = textColor,
        selectTextColor = selectTextColor,
        selectIndicatorColor = selectIndicatorColor,
        onIndicatorClick = {
//            pagerState.setPageIndexWithAnimate(it)
            pagerState.setPageIndex(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        margin = 28.dp,
    )
}