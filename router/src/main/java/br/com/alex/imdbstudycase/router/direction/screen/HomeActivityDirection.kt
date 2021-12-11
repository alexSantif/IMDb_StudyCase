package br.com.alex.imdbstudycase.router.direction.screen

import android.os.Parcelable
import br.com.alex.imdbstudycase.router.direction.ScreenDirection
import kotlinx.android.parcel.Parcelize

interface HomeActivityDirection : ScreenDirection {

    @Parcelize
    data class Params(
        val id: String,
        val enableFinishForResult: Boolean = false
    ) : Parcelable
}