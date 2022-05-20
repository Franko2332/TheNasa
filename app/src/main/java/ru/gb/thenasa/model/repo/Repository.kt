package ru.gb.thenasa.model.repo

import ru.gb.thenasa.model.pojo.ResultPictureOfTheDay
import ru.gb.thenasa.model.pojo.ResultPicturesFromTheMars

interface Repository {
    suspend fun getPictureOfTheDay(): ResultPictureOfTheDay
    suspend fun getPictureOfTheDayWithDate(date: String): ResultPictureOfTheDay
    suspend fun getPicturesFromTheMars(): ResultPicturesFromTheMars
}