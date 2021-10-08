package br.com.alex.imdbstudycase.home.data.model

import com.google.gson.annotations.SerializedName

data class Movies(

    @SerializedName("fullTitle") val fullTitle: String,
    @SerializedName("image") val image: String
)