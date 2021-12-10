package br.com.alex.imdbstudycase.router

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alex.imdbstudycase.core.navigation.LiveEvent
import br.com.alex.imdbstudycase.core.navigation.MutableLiveEvent
import br.com.alex.imdbstudycase.core.navigation.publish
import br.com.alex.imdbstudycase.router.direction.NavDirection
import br.com.alex.imdbstudycase.router.model.NavigationCommand
import br.com.alex.imdbstudycase.router.model.NavigationResult
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.suspendCoroutine

interface NavigableViewModel<T : NavDirection> {
    val router: LiveEvent<NavigationCommand>

    val navigableScope: CoroutineScope
        get() = (this as ViewModel).viewModelScope

    fun MutableLiveEvent<NavigationCommand>.navigateTo(
        direction: NavDirection
    ) {
        publish(NavigationCommand.To(direction))
    }

    suspend fun MutableLiveEvent<NavigationCommand>.navigateForResult(direction: NavDirection): NavigationResult =
        suspendCoroutine { continuation ->
            publish(NavigationCommand.ForResult(direction, continuation))
        }

    fun MutableLiveEvent<NavigationCommand>.navigateToPrevious(direction: T) {
        publish(NavigationCommand.ToPrevious(direction))
    }

    fun MutableLiveEvent<NavigationCommand>.navigateBack() {
        publish(NavigationCommand.Back)
    }

    fun MutableLiveEvent<NavigationCommand>.finish(results: Any? = null) {
        publish(NavigationCommand.Finish(results))
    }
}
