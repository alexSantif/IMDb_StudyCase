package br.com.alex.imdbstudycase.router.direction

import android.os.Bundle
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NavDirectionContext(
    val extra: Parcelable = Bundle.EMPTY
) : Parcelable {

    companion object {
        internal const val CONTEXT_PARAM = "PARAM_NAV_DIRECTION_CONTEXT"
    }
}
