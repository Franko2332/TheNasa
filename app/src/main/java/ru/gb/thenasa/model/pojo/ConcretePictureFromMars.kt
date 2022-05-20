package ru.gb.thenasa.model.pojo

import com.google.gson.annotations.SerializedName

data class ConcretePictureFromMars(
    @SerializedName("id") val id: Int?,
    @SerializedName("sol") val sol: Int?,
    @SerializedName("img_src") val imageUrl: String?
)