package br.com.alex.imdbstudycase.moviedetails.data.model

import com.google.gson.annotations.SerializedName

data class Image(

    @SerializedName("image") val image: String? = null,
    @SerializedName("title") val title: String? = null
)