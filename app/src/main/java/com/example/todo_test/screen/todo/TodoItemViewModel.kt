package com.example.todo_test.screen.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoItemViewModel(
    private val todoItemDao: TodoItemDao
): ViewModel() {

    private val _todoItems = todoItemDao.getAllTodoItems()
    private val _todoText = MutableStateFlow("")


    private val _state = MutableStateFlow(TodoItemState())

    val state = combine(_state, _todoText, _todoItems) {todoItemState: TodoItemState, todoText: String, todoItems: List<TodoItem> ->
        todoItemState.copy(
            todoItems,
            todoText
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), TodoItemState())

    fun insert(todoItem: TodoItem) {
        viewModelScope.launch {
            todoItemDao.insert(todoItem)
            _state.update {
                it.copy(
                    text = ""
                )
            }
        }
    }

}