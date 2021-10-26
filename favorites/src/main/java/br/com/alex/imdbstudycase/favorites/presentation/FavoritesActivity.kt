package br.com.alex.imdbstudycase.favorites.presentation

import br.com.alex.imdbstudycase.core.presentation.BaseActivity
import br.com.alex.imdbstudycase.favorites.R

class FavoritesActivity : BaseActivity() {

    override fun launchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.base_container, FavoritesFragment.newInstance())
            .setReorderingAllowed(true)
            .addToBackStack("favorites")
            .commit()
    }

    override fun configureToolbar() {
        title = "Favoritos"
    }
}