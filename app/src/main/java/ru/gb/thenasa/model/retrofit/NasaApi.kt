package ru.gb.thenasa.model.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.gb.thenasa.model.pojo.ResultPictureOfTheDay

interface NasaApi {
    @GET("planetary/apod")
    suspend fun getPictureOfTheDay(@Query("api_key") apiKey: String,
                                   @Query("thumbs") thumbs: Boolean): ResultPictureOfTheDay
}