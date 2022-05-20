package ru.gb.thenasa.model.retrofit


import retrofit2.http.GET
import retrofit2.http.Query
import ru.gb.thenasa.model.pojo.ResultPictureOfTheDay
import ru.gb.thenasa.model.pojo.ResultPicturesFromTheMars

interface NasaApi {

    @GET("planetary/apod")
    suspend fun getPictureOfTheDay(@Query("api_key") apiKey: String,
                                   @Query("thumbs") thumbs: Boolean,
                                   @Query("date") date: String?): ResultPictureOfTheDay


    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getPicturesFromMars(
        @Query("api_key") apiKey: String,
        @Query("sol") sol: Int,
        @Query("camera") camera: String
    ): ResultPicturesFromTheMars
}