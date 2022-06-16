package ru.gb.thenasa.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.gb.thenasa.App
import ru.gb.thenasa.model.room.ToDoNoteEntity
import ru.gb.thenasa.viewmodel.itemviewmodel.ItemNoteViewModel
import ru.gb.thenasa.viewmodel.itemviewmodel.ItemViewModel

class ToDoNotesViewModel : ViewModel() {
    private var dataBase = App.getMovieDatabase()
    private val data: MutableLiveData<List<ItemViewModel>> = MutableLiveData()
    private val noteData: MutableLiveData<ToDoNoteEntity> = MutableLiveData()

    fun getData(): MutableLiveData<List<ItemViewModel>> {
        val notesViewData = mutableListOf<ItemViewModel>()

        dataBase.NasaToDoNoteDao().allToDoNotes().forEach {
            notesViewData.add(ItemNoteViewModel(it))
        }
        data.postValue(notesViewData)
        return data
    }

    fun saveNote(note: ToDoNoteEntity) {
        dataBase.NasaToDoNoteDao().insert(note)
    }

    fun deleteNote(note: ToDoNoteEntity) {
        dataBase.NasaToDoNoteDao().delete(note)
    }

    fun getNoteById(_id: Int): MutableLiveData<ToDoNoteEntity> {
        noteData.postValue(dataBase.NasaToDoNoteDao().getNoteById(_id))
        return noteData
    }

    fun updateNote(note: ToDoNoteEntity) {
        dataBase.NasaToDoNoteDao().update(note)
    }
}