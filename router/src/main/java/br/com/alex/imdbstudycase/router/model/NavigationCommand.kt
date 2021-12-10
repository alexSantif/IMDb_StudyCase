package br.com.alex.imdbstudycase.router.model

import br.com.alex.imdbstudycase.router.direction.NavDirection
import kotlin.coroutines.Continuation

sealed class NavigationCommand {

    data class ForResult(
        val direction: NavDirection,
        val continuation: Continuation<NavigationResult>
    ) : NavigationCommand()

    data class To(val direction: NavDirection) : NavigationCommand()
    data class ToPrevious(val direction: NavDirection) : NavigationCommand()
    object Back : NavigationCommand()
    data class Finish(val results: Any?) : NavigationCommand()
}
