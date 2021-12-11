package br.com.alex.imdbstudycase.home.presentation

import android.os.Bundle
import br.com.alex.imdbstudycase.core.presentation.BaseActivity
import br.com.alex.imdbstudycase.home.R
import br.com.alex.imdbstudycase.home.di.HomeModule
import br.com.alex.imdbstudycase.router.direction.screen.HomeActivityDirection
import br.com.alex.imdbstudycase.router.ktx.requireNavParam
import org.koin.androidx.viewmodel.ext.android.viewModel
import br.com.alex.imdbstudycase.router.Navigable
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf

class HomeActivity : BaseActivity(), Navigable {

    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViewModel()
    }

    override fun launchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.base_container, HomeFragment.newInstance())
            .setReorderingAllowed(true)
            .addToBackStack("home")
            .commit()
    }

    override fun configureToolbar() {
        title = getString(R.string.home_screen_name)
    }

    private fun bindViewModel() = viewModel.run {
        observeNavigation()
    }
}