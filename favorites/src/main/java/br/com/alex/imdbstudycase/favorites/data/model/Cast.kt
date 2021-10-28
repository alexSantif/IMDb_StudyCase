package br.com.alex.imdbstudycase.favorites.data.model

import com.google.gson.annotations.SerializedName

data class Cast(

    @SerializedName("imDbId") val imDbId: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("fullTitle") val fullTitle: String? = null,
    @SerializedName("type") val type: String? = null,
    @SerializedName("year") val year: String? = null,
    @SerializedName("directors") val directors: List<CastData>? = null,
    @SerializedName("writers") val writers: List<CastData>? = null,
    @SerializedName("others") val others: List<CastData>? = null,
    @SerializedName("actors") val actors: List<Actor>? = null
)