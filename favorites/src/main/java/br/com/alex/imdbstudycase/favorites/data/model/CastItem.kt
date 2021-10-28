package br.com.alex.imdbstudycase.favorites.data.model

import com.google.gson.annotations.SerializedName

data class CastItem(

    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null
)