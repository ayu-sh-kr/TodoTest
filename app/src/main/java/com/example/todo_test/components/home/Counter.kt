package com.example.todo_test.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo_test.ui.theme.Cyan400
import com.example.todo_test.ui.theme.Green400
import com.example.todo_test.ui.theme.Orange400
import com.example.todo_test.ui.theme.Pink400
import com.example.todo_test.ui.theme.Rose400
import com.example.todo_test.ui.theme.Silk400

@Composable
@Preview(showBackground = false)
fun Counter() {
    var count by remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "Counter $count",
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
            color = Orange400
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CounterButton(
                colors = listOf(Green400, Cyan400),
                text = "Decrement",
                onClick = { if (count == 0) 0 else count-- },
            )

            CounterButton(
                onClick = { count++ },
                colors = listOf(Pink400, Silk400, Orange400),
                text = "Increment",
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { count = 0 },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .background(
                    color = Rose400
                )
                .height(40.dp)
                .fillMaxWidth()

        ) {
            Text("Reset")
        }
    }
}


@Composable
fun CounterButton(colors: List<Color>, text: String, onClick: () -> Unit) {

    Button(
        onClick = onClick,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color.Transparent
        ),
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(colors = colors)
            )
            .height(40.dp)
            .wrapContentWidth()
    ) {
        Text(text)
    }

}


