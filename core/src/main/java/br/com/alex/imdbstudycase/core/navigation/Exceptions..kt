package br.com.alex.imdbstudycase.core.navigation

fun illegal(errorMessage: String? = null): Nothing = throw IllegalStateException(errorMessage)

fun unsupported(errorMessage: String? = null): Nothing =
    throw UnsupportedOperationException(errorMessage)
