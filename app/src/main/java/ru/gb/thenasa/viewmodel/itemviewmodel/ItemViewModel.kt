package ru.gb.thenasa.viewmodel.itemviewmodel

import androidx.annotation.LayoutRes

interface ItemViewModel {
    @get: LayoutRes
    val layoutId: Int
    val viewType: Int
        get() = 0
    val dataModel: Any

}