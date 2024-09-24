package com.example.todo_test.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todo_test.components.home.Counter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(showBackground = false)
fun HomeScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box (
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
        ){
            Row (
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp)
            ){
                Text(
                    text = "Counter Component",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
            Counter()
        }
    }
}