package br.com.alex.imdbstudycase.router.ktx

import android.app.Activity
import android.os.Parcelable
import androidx.fragment.app.Fragment
import br.com.alex.imdbstudycase.core.navigation.illegal
import br.com.alex.imdbstudycase.router.direction.NavDirectionContext

val EmptyNavContext = NavDirectionContext()

fun <T : Parcelable> Activity.requireNavParam(): T =
    intent?.getParcelableExtra<NavDirectionContext>(NavDirectionContext.CONTEXT_PARAM)
        ?.extra as? T
        ?: illegal("This intent must contain navigation param.")

fun <T : Parcelable> Fragment.requireNavParam(): T =
    arguments?.getParcelable<NavDirectionContext>(NavDirectionContext.CONTEXT_PARAM)
        ?.extra as? T
        ?: illegal("This bundle must contain navigation param.")