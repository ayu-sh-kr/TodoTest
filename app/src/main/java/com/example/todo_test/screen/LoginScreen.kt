package com.example.todo_test.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo_test.ui.theme.Blue400
import com.example.todo_test.ui.theme.Gray300
import com.example.todo_test.ui.theme.Gray50
import com.example.todo_test.ui.theme.Gray600
import com.example.todo_test.ui.theme.Gray900
import com.example.todo_test.ui.theme.Red300
import com.example.todo_test.ui.theme.Red600
import com.example.todo_test.ui.theme.Slate100
import com.example.todo_test.ui.theme.Slate400


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun LoginScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Gray50)
                .padding(5.dp)
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Spacer(
                    modifier = Modifier.padding(vertical = 20.dp)
                )
                Text(
                    text = "Sign In",
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium,
                    color = Gray900,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                var username by remember { mutableStateOf("") }

                InputField(
                    value = username,
                    onValueChange = { username = it },
                    isPassword = false,
                    placeholder = "",
                    label = "Username"
                )

                InputField(
                    value = username,
                    onValueChange = {},
                    isPassword = true,
                    placeholder = "",
                    label = "Password"
                )

                Text(
                    "Forgot password?",
                    style = TextStyle(
                        color = Blue400,
                        textAlign = TextAlign.End,
                        textDecoration = TextDecoration.Underline,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            paddingValues = PaddingValues(
                                start = 16.dp, end = 16.dp
                            )
                        )
                )

                Button(
                    onClick = {},
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Red600,
                    ),
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 20.dp)
                        .fillMaxWidth()
                ) {
                    Text("Login", style = TextStyle(fontSize = 25.sp, color = Slate100))
                }

                Spacer(
                    modifier = Modifier.padding(vertical = 20.dp)
                )

            }

        }
    }
}

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