package ru.gb.thenasa.model.repo

import ru.gb.thenasa.BuildConfig
import ru.gb.thenasa.model.Const
import ru.gb.thenasa.model.pojo.ResultPictureOfTheDay
import ru.gb.thenasa.model.pojo.ResultPicturesFromTheMars
import ru.gb.thenasa.model.retrofit.RetrofitService

class NasaRepositoryImpl : Repository {
    override suspend fun getPictureOfTheDay(): ResultPictureOfTheDay {
        return RetrofitService.getNasaApiService().getPictureOfTheDay(
            BuildConfig.NASA_API_KEY,
            true, null
        )
    }

    override suspend fun getPictureOfTheDayWithDate(date: String): ResultPictureOfTheDay {
        return RetrofitService.getNasaApiService().getPictureOfTheDay(
            BuildConfig.NASA_API_KEY,
            true, date
        )
    }

    override suspend fun getPicturesFromTheMars(): ResultPicturesFromTheMars {
        return RetrofitService.getNasaApiService().getPicturesFromMars(BuildConfig.NASA_API_KEY, 1000, Const.RoverCamerasNames.MAST
        )
    }
}