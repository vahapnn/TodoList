package com.example.todolist.repository

import android.util.Log
import com.example.todolist.database.NoteDatabase
import com.example.todolist.models.Note

class NoteRepository(private val db:NoteDatabase) {
    suspend fun upSert(note: Note){
        try {
            db.noteDao.upSert(note)
        }catch (e:Exception){
            Log.d("Error",e.message.toString())
        }
    }
    suspend fun delete(note: Note){
        try {
            db.noteDao.delete(note)
        }catch (e:Exception){
            Log.d("Error",e.message.toString())
        }
    }
    fun getAllNotes()=db.noteDao.getAllNotes()
}