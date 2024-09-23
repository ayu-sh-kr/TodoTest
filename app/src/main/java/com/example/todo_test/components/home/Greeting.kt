package com.example.todo_test.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
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