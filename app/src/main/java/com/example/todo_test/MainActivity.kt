package com.example.todo_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo_test.ui.theme.TodoTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoTestTheme {
                Scaffold { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Greeting(
                            name = "Ayush Kumar Jaiswal",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    var count by remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        Button(
            onClick = {count++},
            modifier = Modifier.
            padding(top = 10.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
            ,
            colors = ButtonColors(
                containerColor = Color.Cyan,
                contentColor = Color.Blue,
                disabledContainerColor = Color.Cyan,
                disabledContentColor = Color.Blue,
            )
        ) {
            Text(
                text = "Click Me $count",
            )
        }
    }
}