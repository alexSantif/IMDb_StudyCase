package br.com.alex.imdbstudycase.favorites.data.model

import com.google.gson.annotations.SerializedName

data class CastData(

    @SerializedName("job") val job: String? = null,
    @SerializedName("items") val items: List<CastItem>? = null
)