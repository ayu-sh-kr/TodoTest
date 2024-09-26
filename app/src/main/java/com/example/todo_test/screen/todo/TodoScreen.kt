package com.example.todo_test.screen.todo

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import com.example.todo_test.components.todo.TodoForm
import com.example.todo_test.components.todo.TodoItemList
import com.example.todo_test.ui.theme.Indigo300
import com.example.todo_test.ui.theme.Indigo700
import com.example.todo_test.ui.theme.Slate50
import kotlinx.coroutines.flow.Flow
import java.util.Date


@Entity
data class TodoItem(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String,
    var isDone: Boolean = false,
    val date: Date = Date()
)

@Dao
interface TodoItemDao {
    @Insert
    fun insert(todoItem: TodoItem)

    @Query("SELECT * FROM TodoItem")
    fun getAllTodoItems(): Flow<List<TodoItem>>
}

@Database(entities = [TodoItem::class], version = 1)
abstract class TodoItemDatabase: RoomDatabase() {
    abstract fun todoItemDao(): TodoItemDao
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TemporaryTodoScreen(state: TodoItemState, insert: (TodoItem) -> Unit) {

    var items = state.todoItems

    var showPopover by remember { mutableStateOf(false) }

    var currentId by remember { mutableIntStateOf(2) }

    Scaffold(
        topBar = {
            Text(
                "Todos",
                style = TextStyle(
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Medium,
                    color = Indigo700
                ),
                modifier = Modifier
                    .padding(vertical = 40.dp)
                    .background(color = Indigo300, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 16.dp, vertical = 5.dp)
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = { showPopover = true }
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Todo")
            }
        },
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Slate50)
                .padding(vertical = 100.dp)
        ) {
            TodoItemList(
                items = items,
                onTodoCheckedChange = {id, isChecked ->
                    items = items.map {
                        if(it.id == id) it.copy(id = it.id, text = it.text, isDone = isChecked) else it
                    }
                    println("Called onTodoCheckedChange")
                },
                onTodoDelete = {id ->
                    items = items.filter {
                        it.id != id
                    }
                }
            )

            if (showPopover) {
                TodoFormPopover(
                    onDismiss = {showPopover = false},
                    onSubmit = {
                        insert(TodoItem(text = it))
                        currentId++
                        showPopover = false
                    }
                )
            }

        }
    }
}

@Composable
fun TodoFormPopover(onDismiss: () -> Unit, onSubmit: (String) -> Unit) {
    Popup(
        onDismissRequest = onDismiss,
        alignment = Alignment.Center,
        properties = PopupProperties(
            focusable = true
        )
    ) {
        TodoForm(onSubmit)
    }
}
