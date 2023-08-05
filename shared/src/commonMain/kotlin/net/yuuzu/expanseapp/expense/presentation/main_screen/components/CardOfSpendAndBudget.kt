package net.yuuzu.expanseapp.expense.presentation.main_screen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CardOfSpendAndBudget(
    title: String,
    remainingCost: Int,
    budgetCost: Int,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    shape: RoundedCornerShape = RoundedCornerShape(25.dp)
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(0.dp, shape = shape)
            .clip(shape = shape)
            .background(
                color = backgroundColor,
                shape = shape
            )
            .animateContentSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                color = Color.Gray,
                style = MaterialTheme.typography.titleSmall,
            )

            Spacer(modifier = Modifier.height(24.dp))

            GradientProgressbar(
                remainingCost = remainingCost,
                budgetCost = budgetCost
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "$$remainingCost",
                    color = if (remainingCost <= 0) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleLarge,
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = "/$$budgetCost",
                    color = Color.Gray,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}