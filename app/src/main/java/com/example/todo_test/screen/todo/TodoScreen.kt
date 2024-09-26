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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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
import androidx.room.TypeConverter
import androidx.room.TypeConverters
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

    @Query("UPDATE TodoItem SET isDone = :status WHERE id = :id")
    fun updateItemStatus(id: Int, status: Boolean)

    @Query("DELETE FROM TodoItem WHERE id = :id")
    fun deleteItemById(id: Int)
}

@Database(entities = [TodoItem::class], version = 1)
@TypeConverters(Converters::class)
abstract class TodoItemDatabase: RoomDatabase() {
    abstract fun todoItemDao(): TodoItemDao
}

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TemporaryTodoScreen(todoItemViewModel: TodoItemViewModel) {

    val items = remember { mutableStateListOf<TodoItem>() }

    LaunchedEffect(Unit) {
        todoItemViewModel.getItems().collect { value ->
            items.clear()
            items.addAll(value)
        }
    }

    val insert = todoItemViewModel::insert
    val check = todoItemViewModel::check
    val delete = todoItemViewModel::delete

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
                    check(id, isChecked)
                },
                onTodoDelete = {id ->
                    delete(id)
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
