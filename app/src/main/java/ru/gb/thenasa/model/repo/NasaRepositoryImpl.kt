package ru.gb.thenasa.model.repo

import ru.gb.thenasa.BuildConfig
import ru.gb.thenasa.model.pojo.ResultPictureOfTheDay
import ru.gb.thenasa.model.retrofit.RetrofitService

class NasaRepositoryImpl:Repository {
    override suspend fun getPictureOfTheDay(): ResultPictureOfTheDay {
        return RetrofitService.getNasaApiService().getPictureOfTheDay(BuildConfig.NASA_API_KEY,
            true)
    }
}