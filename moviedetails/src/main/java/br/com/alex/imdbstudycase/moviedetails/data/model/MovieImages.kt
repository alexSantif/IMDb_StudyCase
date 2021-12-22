package br.com.alex.imdbstudycase.moviedetails.data.model

import com.google.gson.annotations.SerializedName

data class MovieImages(

    @SerializedName("imDbId") val id: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("fullTitle") val fullTitle: String? = null,
    @SerializedName("year") val year: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("items") val items: List<Image>? = null
)