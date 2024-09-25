package com.example.todo_test.components.todo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_test.ui.theme.Gray400
import com.example.todo_test.ui.theme.Indigo300
import com.example.todo_test.ui.theme.Indigo700
import com.example.todo_test.ui.theme.Slate100
import com.example.todo_test.ui.theme.Slate50
import com.example.todo_test.ui.theme.Slate700
import com.example.todo_test.ui.theme.Slate800

@Composable
fun TodoForm(
    onSubmit: (String) -> Unit
) {
    var todoText by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
            .border(width = 1.dp, color = Gray400, shape = RoundedCornerShape(20.dp))
            .height(250.dp)
            .padding(10.dp)
    ) {

        OutlinedTextField(
            value = todoText,
            onValueChange = {todoText = it},
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
            ,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Slate50,
                focusedContainerColor = Slate100,
                focusedTextColor = Slate800,
                unfocusedTextColor = Slate700
            ),
            placeholder = { Text("Enter the Text") },
            shape = RoundedCornerShape(12.dp),
            label = { Text("Text") }
        )

        Button(
            onClick = { onSubmit(todoText) },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Indigo300
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                "Add", style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Indigo700
                )
            )
        }
    }
}