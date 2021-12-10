package br.com.alex.imdbstudycase.router

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import androidx.core.app.NavUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import br.com.alex.imdbstudycase.core.navigation.illegal
import br.com.alex.imdbstudycase.core.navigation.observeAndConsumeNonNull
import br.com.alex.imdbstudycase.core.navigation.unsupported
import br.com.alex.imdbstudycase.router.direction.NavDirection
import br.com.alex.imdbstudycase.router.dsl.doWhen
import br.com.alex.imdbstudycase.router.ktx.requireIntent
import br.com.alex.imdbstudycase.router.model.NavigationCommand
import br.com.alex.imdbstudycase.router.model.NavigationResult.Companion.RESULT_PARAM
import kotlinx.coroutines.CoroutineScope

interface Navigable {

    fun <T : NavDirection> NavigableViewModel<T>.observeNavigation(delegate: Navigable? = null) {
        router.observeAndConsumeNonNull(getLifecycleOwner()) {
            val navigable = delegate ?: this@Navigable
            it.handleNavigation(navigableScope, navigable)
        }
    }

    fun navigateTo(direction: NavDirection) {
        direction.doWhen {
            isFragment {
                illegal("You should manually implement 'navigateTo()' if you want to navigate to a Fragment.")
            }
            isIntent {
                navigateToActivity(requireIntent())
            }
            otherDirection { unsupported() }
        }
    }

    fun navigateToPrevious(direction: NavDirection) {
        direction.doWhen {
            isFragment {
                illegal("You should manually implement 'navigateToPrevious()' to navigate to a previous Fragment.")
            }
            isIntent {
                NavUtils.navigateUpTo(requireActivity(), requireIntent())
            }
            otherDirection { unsupported() }
        }
    }

    fun navigateBack() {
        requireActivity().onBackPressed()
    }

    fun navigateFinish(results: Any?) {
        requireActivity().run {
            results?.let {
                val parcelables = when (it) {
                    is Parcelable -> listOf(it)
                    is List<*> -> it.filterIsInstance<Parcelable>()
                    else -> illegal(
                        errorMessage = "You should pass to the finish() method " +
                                "only 'Parcelable' or 'List<Parcelable>' variable types."
                    )
                }
                val intent =
                    Intent().putParcelableArrayListExtra(RESULT_PARAM, ArrayList(parcelables))
                setResult(Activity.RESULT_OK, intent)
            }
            finishAfterTransition()
        }
    }

    private fun NavigationCommand.handleNavigation(
        scope: CoroutineScope,
        navigable: Navigable
    ) {
        when (this) {
            is NavigationCommand.To -> navigable.navigateTo(direction)
            is NavigationCommand.ToPrevious -> navigable.navigateToPrevious(direction)
            is NavigationCommand.Back -> navigable.navigateBack()
            is NavigationCommand.Finish -> navigable.navigateFinish(results)
            else -> {}
        }
    }

    private fun getLifecycleOwner() = when (this) {
        is FragmentActivity -> this
        is Fragment -> viewLifecycleOwner
        else -> illegal(CONTEXT_ERROR)
    }

    private fun requireActivity(): Activity = when (this) {
        is FragmentActivity -> this
        is Fragment -> this.requireActivity()
        else -> illegal(CONTEXT_ERROR)
    }

    private fun navigateToActivity(intent: Intent) = when (this) {
        is FragmentActivity -> startActivity(intent)
        is Fragment -> startActivity(intent)
        else -> illegal(CONTEXT_ERROR)
    }

    companion object {
        private const val CONTEXT_ERROR =
            "This context is not handled... The Navigable interface supports only [Fragment] & [FragmentActivity]" +
                    " (or [ContextAware] objects if they only want to launch an activity)"
    }
}
