package com.example.todolist.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.todolist.models.Note
import com.example.todolist.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRepository: NoteRepository):ViewModel() {
    val notes:MutableState<List<Note>> = mutableStateOf(emptyList())
    fun getAllNote()=noteRepository.getAllNotes().asLiveData(viewModelScope.coroutineContext)
    fun upSert(note: Note){
        viewModelScope.launch {
            noteRepository.upSert(note)
        }
    }
    fun delete(note: Note){
        viewModelScope.launch {
            noteRepository.delete(note)
        }
    }
}