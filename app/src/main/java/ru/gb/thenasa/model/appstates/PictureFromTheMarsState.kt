package ru.gb.thenasa.model.appstates

import ru.gb.thenasa.model.pojo.ResultPictureOfTheDay
import ru.gb.thenasa.model.pojo.ResultPicturesFromTheMars

sealed class PictureFromTheMarsState {

    object Loading : PictureFromTheMarsState()
    data class Success(val result: ResultPicturesFromTheMars) : PictureFromTheMarsState()
    data class Error(val error: Throwable) : PictureFromTheMarsState()
}