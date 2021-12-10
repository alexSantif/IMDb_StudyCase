package br.com.alex.imdbstudycase.router.direction

import br.com.alex.imdbstudycase.core.navigation.getFromDI
import br.com.alex.imdbstudycase.router.ktx.EmptyNavContext
import org.koin.core.parameter.parametersOf

typealias Screen = Any

interface NavDirection

@Suppress("SpreadOperator")
inline fun <reified T : NavDirection> getNavImpl(vararg param: Any?): T =
    getFromDI {
        parametersOf(*param)
    }

interface ScreenDirection : NavDirection {

    val navContext: NavDirectionContext
        get() = EmptyNavContext

    fun toScreen(): Screen
}
