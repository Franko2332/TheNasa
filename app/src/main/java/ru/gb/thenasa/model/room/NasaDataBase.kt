package ru.gb.thenasa.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ToDoNoteEntity::class], version = 1, exportSchema = false)
abstract class NasaDataBase: RoomDatabase() {
    abstract fun NasaToDoNoteDao(): ToDoNoteDao
}