package br.com.alex.imdbstudycase.moviedetails.presentation

import br.com.alex.imdbstudycase.core.presentation.BaseActivity
import br.com.alex.imdbstudycase.moviedetails.R

class MovieDetailsActivity : BaseActivity() {

    override fun launchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.base_container, MovieDetailsFragment.newInstance())
            .setReorderingAllowed(true)
            .addToBackStack("home")
            .commit()
    }

    override fun configureToolbar() {
        title = "Detalhes"
    }
}