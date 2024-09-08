package com.example.todolist.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.todolist.models.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Upsert
  suspend fun upSert(note: Note)
  @Delete
  suspend fun delete(note: Note)
  @Query("select * from note")
  fun getAllNotes():Flow<List<Note>>
}