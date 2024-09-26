package com.example.todo_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.todo_test.screen.todo.TemporaryTodoScreen
import com.example.todo_test.screen.todo.TodoItemDatabase
import com.example.todo_test.screen.todo.TodoItemViewModel
import com.example.todo_test.ui.theme.TodoTestTheme

class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            TodoItemDatabase::class.java,
            "todo_items.db"
        ).build()
    }

    private val viewModel by viewModels<TodoItemViewModel> (
        factoryProducer = {
            object: ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return TodoItemViewModel(db.todoItemDao()) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoTestTheme {
                TemporaryTodoScreen(viewModel)
            }
        }
    }
}