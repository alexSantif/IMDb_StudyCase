package br.com.alex.imdbstudycase.home

import br.com.alex.imdbstudycase.core.launchFragment
import br.com.alex.imdbstudycase.home.presentation.HomeFragment

class HomeRobot {

    private lateinit var fragment: HomeFragment

    fun launch(robotCommands: HomeRobot.() -> Unit) {
        launchFragment(
            HomeFragment()
        ) {
            fragment = this
            robotCommands.invoke(this@HomeRobot)
        }
    }
}