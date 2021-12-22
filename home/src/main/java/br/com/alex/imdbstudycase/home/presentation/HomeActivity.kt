package br.com.alex.imdbstudycase.home.presentation

import br.com.alex.imdbstudycase.core.presentation.BaseActivity
import br.com.alex.imdbstudycase.home.R

class HomeActivity : BaseActivity() {

    override fun launchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.base_container, HomeFragment())
            .setReorderingAllowed(true)
            .addToBackStack(FRAGMENT_TAG)
            .commit()
    }

    override fun configureToolbar() {
        title = getString(R.string.home_screen_name)
    }

    companion object {

        private const val FRAGMENT_TAG = "home"
    }
}