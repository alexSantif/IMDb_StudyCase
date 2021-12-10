package br.com.alex.imdbstudycase.presentation

import androidx.lifecycle.ViewModel
import br.com.alex.imdbstudycase.core.navigation.MutableLiveEvent
import br.com.alex.imdbstudycase.home.navigation.HomeDirections
import br.com.alex.imdbstudycase.router.NavigableViewModel
import br.com.alex.imdbstudycase.router.model.NavigationCommand

class MainViewModel() : ViewModel(),
    NavigableViewModel<HomeDirections> {

    override val router = MutableLiveEvent<NavigationCommand>()

    fun navigateTo() {
        router.navigateTo(HomeDirections.FeatureActivity("1234"))
    }
}