package com.example.todolist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolist.models.Note
import com.example.todolist.viewmodel.NoteViewModel

@Composable
fun NotePage(noteViewModel: NoteViewModel,modifier: Modifier=Modifier){
  var yapilacakAd by remember {
      mutableStateOf("")
  }
    var yapilacakIcerik by remember {
        mutableStateOf("")
    }
    val note=Note(yapilacakAd,yapilacakIcerik)
    var noteList by remember {
        mutableStateOf(listOf<Note>())
    }


}
