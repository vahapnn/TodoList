package com.example.todolist.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
  val noteName:String,
    val noteBody:String,
  @PrimaryKey(autoGenerate = true)
    val id:Int=0
)
