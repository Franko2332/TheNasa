package ru.gb.thenasa.model

import ru.gb.thenasa.model.pojo.ResultPictureOfTheDay

sealed class PictureOfTheDayState {
    data class Success(val resultUrl: String?, val explanation: String?) : PictureOfTheDayState()
    data class Error(val error: Throwable): PictureOfTheDayState()
    object Loading: PictureOfTheDayState()
}