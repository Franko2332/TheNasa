package ru.gb.thenasa.viewmodel.itemviewmodel

import ru.gb.thenasa.R
import ru.gb.thenasa.model.room.ToDoNoteEntity

class ItemNoteViewModel(override val dataModel: ToDoNoteEntity): ItemViewModel {
    override val layoutId = R.layout.item_to_do_note
    override val viewType = 1
}