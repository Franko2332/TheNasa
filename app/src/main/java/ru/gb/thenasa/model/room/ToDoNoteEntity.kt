package ru.gb.thenasa.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoNoteEntity(@PrimaryKey
                          val id: Int,
                          val title: String?,
                          val description: String?,
                          val date: String
)