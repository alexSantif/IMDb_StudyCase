package br.com.alex.imdbstudycase.presentation

import android.os.Bundle
import br.com.alex.imdbstudycase.R
import br.com.alex.imdbstudycase.core.presentation.BaseActivity

class SplashActivity : BaseActivity() {

    override fun launchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.base_container, SplashFragment.newInstance())
            .setReorderingAllowed(true)
            .addToBackStack("splash")
            .commit()
    }

    override fun configureToolbar() {
        title = "Splash Screen"
    }
}