package com.example.todo_test.components.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_test.screen.todo.TodoItem
import com.example.todo_test.ui.theme.Blue400
import com.example.todo_test.ui.theme.Gray800
import com.example.todo_test.ui.theme.Indigo100
import com.example.todo_test.ui.theme.Indigo600
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TodoContent(
    item: TodoItem,
    onCheckedChange: (Boolean) -> Unit,
    onDeleteClick: () -> Unit
) {

    val complete = SwipeAction(
        icon = {},
        background = Blue400,
        onSwipe = {onCheckedChange(!item.isDone)}
    )

    SwipeableActionsBox(
        startActions = listOf(complete),
        swipeThreshold = 100.dp,
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

            Column {

                val dateFormat = SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.getDefault())

                Text(
                    dateFormat.format(item.date),
                    style = TextStyle(
                        fontSize = 10.sp,
                        color = Indigo600,
                        fontWeight = FontWeight.Medium
                    )
                )

                Spacer(modifier = Modifier.padding(vertical = 2.dp))

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