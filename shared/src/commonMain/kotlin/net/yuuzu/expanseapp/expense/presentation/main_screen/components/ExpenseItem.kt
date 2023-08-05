package net.yuuzu.expanseapp.expense.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.yuuzu.expanseapp.expense.domain.Expense

@Composable
fun ExpenseItem(
    expense: Expense,
    itemColor: Color,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    iconShape: RoundedCornerShape = RoundedCornerShape(16.dp),
    shape: RoundedCornerShape = RoundedCornerShape(24.dp),
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = iconShape)
            .background(color = backgroundColor, shape = shape)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp, 50.dp)
                    .clip(shape = iconShape)
                    .background(
                        color = itemColor,
                        shape = iconShape
                    )
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = expense.title,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.titleSmall,
                )

                Text(
                    text = expense.category,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

            Text(
                text = "${expense.amount}",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
            )
        }
    }
}