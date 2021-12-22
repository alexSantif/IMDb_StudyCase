package br.com.alex.imdbstudycase.home.data.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @SerializedName("items") val items: List<MovieData>? = null
)