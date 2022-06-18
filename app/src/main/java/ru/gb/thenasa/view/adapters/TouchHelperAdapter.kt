package ru.gb.thenasa.view.adapters

import java.text.FieldPosition

interface TouchHelperAdapter {

    fun onItemMoved(from: Int, to: Int)
    fun onItemDismissed(position: Int)
}