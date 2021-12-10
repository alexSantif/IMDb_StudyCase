package br.com.alex.imdbstudycase.home.navigation

import br.com.alex.imdbstudycase.router.direction.NavDirection
import br.com.alex.imdbstudycase.router.direction.getNavImpl
import br.com.alex.imdbstudycase.router.direction.screen.FeatureActivityDirection
import br.com.alex.imdbstudycase.router.direction.screen.FeatureFragmentDirection

sealed class HomeDirections : NavDirection {
    data class FeatureActivity(
        private val id: String,
        private val enableFinishForResult: Boolean = false
    ) : HomeDirections(), FeatureActivityDirection by getNavImpl(
        FeatureActivityDirection.Params(id, enableFinishForResult)
    )
    data class FeatureFragment(
        private val id: String
    ) : HomeDirections(), FeatureFragmentDirection by getNavImpl(
        FeatureFragmentDirection.Params(id)
    )
}
