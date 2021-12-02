package br.com.alex.imdbstudycase.moviedetails.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetails(

    @SerializedName("id") val id: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("fullTitle") val fullTitle: String? = null,
    @SerializedName("year") val year: String? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("releaseDate") val releaseDate: String? = null,
    @SerializedName("runtimeStr") val runtimeStr: String? = null,
    @SerializedName("plot") val plot: String? = null,
    @SerializedName("plotLocal") val plotLocal: String? = null,
    @SerializedName("directors") val directors: String? = null,
    @SerializedName("writers") val writers: String? = null,
    @SerializedName("actorList") val actorList: List<Actor>? = null
)