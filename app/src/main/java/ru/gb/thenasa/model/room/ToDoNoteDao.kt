package ru.gb.thenasa.model.room

import androidx.room.*

@Dao
interface ToDoNoteDao {

    @Query("SELECT * FROM ToDoNoteEntity")
    fun allToDoNotes(): List<ToDoNoteEntity>

    @Query("SELECT * FROM ToDoNoteEntity WHERE id LIKE :NoteId")
    fun getNoteById(NoteId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: ToDoNoteEntity)

    @Update
    fun update(entity: ToDoNoteEntity)

    @Delete
    fun delete(entity: ToDoNoteEntity)
}