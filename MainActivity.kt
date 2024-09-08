package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.todolist.database.NoteDatabase
import com.example.todolist.models.Note
import com.example.todolist.repository.NoteRepository
import com.example.todolist.ui.NotePage
import com.example.todolist.ui.theme.TodoListTheme
import com.example.todolist.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java,
            name = "note.db"
        ).build()
    }
    private val viewModel by viewModels<NoteViewModel> (
        factoryProducer = {
           object :ViewModelProvider.Factory{
               override fun <T : ViewModel> create(modelClass: Class<T>): T {
                   return  NoteViewModel(NoteRepository(db)) as T
               }
           }
        }
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var name by remember {
                mutableStateOf("")
            }
            var body by remember {
                mutableStateOf("")
            }
            val note= Note(
                name,body
            )
            var noteList by remember {
                mutableStateOf(listOf<Note>())
            }
            viewModel.getAllNote().observe(this){
                noteList=it
            }
            Column(
                Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { viewModel.upSert(note)}) {
                    Text(text = "Lütfen Yeni yapılacak ekleyiniz....")
                }
                TextField(value = name, onValueChange = {name=it}, placeholder = { Text(text = "Lütfen görev adını giriniz...") })
                TextField(value = body, onValueChange = {body=it}, placeholder = { Text(text = "Lütfen yapılacak görevi giriniz...") })
                LazyColumn {
                    items(noteList){note->
                        Column(Modifier.clickable {
                            viewModel.delete(note)
                        }) {
                            Text(text = "Görev adı: : ${note.noteName}")
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(text = "Yapılcak görev: : ${note.noteBody}")
                            HorizontalDivider(Modifier.fillMaxWidth().padding(6.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoListTheme {
        Greeting("Android")
    }
}