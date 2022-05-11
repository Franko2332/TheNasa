package ru.gb.thenasa.model.repo

import android.telecom.Call
import ru.gb.thenasa.model.pojo.ResultPictureOfTheDay

interface Repository {
    suspend fun getPictureOfTheDay(): ResultPictureOfTheDay
}