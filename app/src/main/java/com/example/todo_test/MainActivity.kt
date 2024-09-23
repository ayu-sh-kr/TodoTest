package com.example.todo_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.todo_test.screen.HomeScreen
import com.example.todo_test.ui.theme.TodoTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoTestTheme {
                HomeScreen()
            }
        }
    }
}