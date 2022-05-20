package ru.gb.thenasa.model.appstates

sealed class PictureOfTheDayState {
    data class Success(val resultUrl: String?, val explanation: String?) : PictureOfTheDayState()
    data class Error(val error: Throwable): PictureOfTheDayState()
    object Loading: PictureOfTheDayState()
}