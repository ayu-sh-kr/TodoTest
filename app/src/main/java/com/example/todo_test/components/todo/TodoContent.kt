package com.example.todo_test.components.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_test.screen.TodoItem
import com.example.todo_test.ui.theme.Gray800
import com.example.todo_test.ui.theme.Indigo100
import com.example.todo_test.ui.theme.Indigo600
import com.example.todo_test.ui.theme.Red400
import com.example.todo_test.ui.theme.Red700
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun TodoContent(
    item: TodoItem,
    onCheckedChange: (Boolean) -> Unit,
    onDeleteClick: () -> Unit
) {

    val delete = SwipeAction(
        icon = {
            Icon(Icons.Filled.Delete, contentDescription = "Delete Action", tint = Red700)
        },
        background = Red400,
        onSwipe = {onDeleteClick()}

    )

    SwipeableActionsBox(
        endActions = listOf(delete),
        swipeThreshold = 100.dp,
        backgroundUntilSwipeThreshold = MaterialTheme.colorScheme.surfaceColorAtElevation(100.dp),
        modifier = Modifier
            .padding(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .background(color = Indigo100, shape = RoundedCornerShape(8.dp))
                .border(width = 0.dp, color = Color.Transparent, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 5.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {

                Checkbox(
                    checked = item.isDone,
                    onCheckedChange = onCheckedChange
                )

                Text(
                    item.text,
                    style = TextStyle(
                        fontSize = 20.sp,
                        textDecoration = if (item.isDone) TextDecoration.LineThrough else TextDecoration.None,
                        color = Gray800
                    )
                )

            }

            IconButton(
                onClick = onDeleteClick
            ) {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = "Delete Item",
                    tint = Indigo600
                )
            }

        }
    }
}