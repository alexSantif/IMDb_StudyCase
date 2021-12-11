package br.com.alex.imdbstudycase.moviedetails.presentation

import android.view.MenuItem
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
        title = getString(R.string.movie_details_screen_name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}