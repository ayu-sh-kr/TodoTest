package com.example.todo_test.components.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_test.ui.theme.Gray300
import com.example.todo_test.ui.theme.Gray50
import com.example.todo_test.ui.theme.Gray600
import com.example.todo_test.ui.theme.Gray900
import com.example.todo_test.ui.theme.Red300
import com.example.todo_test.ui.theme.Slate400

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean,
    placeholder: String
) {
    Column(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 10.dp
        )
    ) {
        Text(
            text = label,
            color = Slate400,
            fontSize = 18.sp,
            fontWeight = FontWeight(500),
            modifier = Modifier.padding(bottom = 4.dp)
        )

        val interactionSource = remember { MutableInteractionSource() }
        val isFocused by interactionSource.collectIsFocusedAsState()


        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                color = Gray900,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Gray50, shape = RoundedCornerShape(8.dp))
                .height(40.dp)
                .border(
                    width = if (isFocused) 3.dp else 0.dp,
                    color = if (isFocused) Red300 else Gray300,
                    shape = RoundedCornerShape(8.dp),
                )
                .padding(10.dp),
            interactionSource = interactionSource,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = Gray600
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}