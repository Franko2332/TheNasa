package ru.gb.thenasa.model.pojo

import com.google.gson.annotations.SerializedName

data class ResultPictureOfTheDay(
    @field:SerializedName("copyright") val copyright: String?,
    @field:SerializedName("explanation") val explanation: String?,
    @field:SerializedName("date") val date: String?,
    @field:SerializedName("media_type") val mediaType: String?,
    @field:SerializedName("service_version") val serviceVersion: String?,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("url") val url: String?,
    @field:SerializedName("hdurl") val hdurl: String?,
    @field:SerializedName("thumbnail_url") val thumbnailUrl: String?
)
