package br.com.alex.imdbstudycase.favorites.presentation

import android.view.MenuItem
import br.com.alex.imdbstudycase.core.presentation.BaseActivity
import br.com.alex.imdbstudycase.favorites.R

class FavoritesActivity : BaseActivity() {

    override fun launchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.base_container, FavoritesFragment.newInstance())
            .setReorderingAllowed(true)
            .addToBackStack(FRAGMENT_TAG)
            .commit()
    }

    override fun configureToolbar() {
        title = getString(R.string.favorites_screen_name)
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

    companion object {

        private const val FRAGMENT_TAG = "favorites"
    }
}