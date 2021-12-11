package br.com.alex.imdbstudycase.home.navigation

import android.content.Context
import android.content.Intent
import br.com.alex.imdbstudycase.home.presentation.HomeActivity
import br.com.alex.imdbstudycase.router.direction.NavDirectionContext
import br.com.alex.imdbstudycase.router.direction.Screen
import br.com.alex.imdbstudycase.router.direction.screen.HomeActivityDirection

class HomeActivityDirectionImpl(
    private val context: Context,
    params: HomeActivityDirection.Params
) : HomeActivityDirection {

    override val navContext = NavDirectionContext(extra = params)
    override fun toScreen(): Screen = Intent(context, HomeActivity::class.java)
}