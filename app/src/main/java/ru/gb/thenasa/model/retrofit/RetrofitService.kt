package ru.gb.thenasa.model.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.thenasa.model.Const

object RetrofitService {
    private var retrofit: Retrofit? = null
    fun getNasaApiService(): NasaApi {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder().apply {
                    addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY

                    })
                }.build())
                .build()
        }
        return retrofit!!.create(NasaApi::class.java)
    }


}