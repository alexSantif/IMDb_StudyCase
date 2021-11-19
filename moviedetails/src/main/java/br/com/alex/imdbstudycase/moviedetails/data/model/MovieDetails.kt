package br.com.alex.imdbstudycase.moviedetails.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetails(

    @SerializedName("id") val id: String? = null,
    @SerializedName("title") val title: String? = null
)