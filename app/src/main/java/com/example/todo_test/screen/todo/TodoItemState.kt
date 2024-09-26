package com.example.todo_test.screen.todo

data class TodoItemState(
    val todoItems: List<TodoItem> = emptyList(),
    val text: String = ""
)