package br.com.alex.imdbstudycase.router.model

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import br.com.alex.imdbstudycase.core.navigation.illegal
import kotlinx.android.parcel.Parcelize

data class NavigationResult(
    val code: Int,
    val data: Intent?
) {

    fun <T : Parcelable> getParcelable(): T =
        requireNotNull(data)
            .getParcelableArrayListExtra<T>(RESULT_PARAM)
            ?.toList()
            ?.first()
            ?: illegal("The data doesn't contain any extra.")

    fun <T : Parcelable> getParcelables(): List<T> =
        requireNotNull(data)
            .getParcelableArrayListExtra<T>(RESULT_PARAM)
            ?.toList()
            ?: illegal("The data doesn't contain any extra.")

    val hasBeenCanceled: Boolean
        get() = code == Activity.RESULT_CANCELED

    val hasData: Boolean
        get() = code == Activity.RESULT_OK

    @Parcelize
    internal object SuccessWithNoResult : Parcelable

    companion object {
        internal const val RESULT_PARAM = "RESULT_PARAM"
    }
}
