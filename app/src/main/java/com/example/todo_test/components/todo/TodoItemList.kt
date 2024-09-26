package com.example.todo_test.components.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import com.example.todo_test.screen.todo.TodoItem

@Composable
fun TodoItemList(
    items: List<TodoItem>,
    onTodoCheckedChange: (Int, Boolean) -> Unit,
    onTodoDelete: (Int) -> Unit
) {
    val scrollState = rememberScrollState();
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        for (item in items) {
            key(item.id) {
                TodoContent(
                    item = item,
                    onCheckedChange = {
                        onTodoCheckedChange(item.id, !item.isDone)
                    },
                    onDeleteClick = {
                        onTodoDelete(item.id)
                    }
                )
            }
        }
    }
}