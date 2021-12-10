package br.com.alex.imdbstudycase.core.navigation

import org.koin.core.context.GlobalContext
import org.koin.core.parameter.ParametersDefinition

inline fun <reified T> getFromDI(
    noinline parameters: ParametersDefinition? = null
): T = GlobalContext.get().get(parameters = parameters)
