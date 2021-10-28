package br.com.alex.imdbstudycase.favorites.data.model

import com.google.gson.annotations.SerializedName

data class Actor (

    @SerializedName("id") val id: String? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("asCharacter") val asCharacter: String? = null
)