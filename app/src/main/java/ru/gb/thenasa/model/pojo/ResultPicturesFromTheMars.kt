package ru.gb.thenasa.model.pojo

import com.google.gson.annotations.SerializedName

data class ResultPicturesFromTheMars(
    @SerializedName("photos") val result: ArrayList<ConcretePictureFromMars>?
)