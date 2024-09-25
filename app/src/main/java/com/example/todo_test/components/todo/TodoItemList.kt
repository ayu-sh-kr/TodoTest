package com.example.todo_test.components.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import com.example.todo_test.screen.TodoItem

@Composable
fun TodoItemList(
    items: List<TodoItem>,
    onTodoCheckedChange: (Int, Boolean) -> Unit,
    onTodoDelete: (Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        for (item in items) {
            key(item.id) {
                TodoContent(
                    item = item,
                    onCheckedChange = {
                        onTodoCheckedChange(item.id, !item.isDone)
                        println("Check box clicked")
                    },
                    onDeleteClick = {
                        onTodoDelete(item.id)
                    }
                )
            }
        }
    }
}