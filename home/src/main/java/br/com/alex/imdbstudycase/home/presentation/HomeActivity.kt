package br.com.alex.imdbstudycase.home.presentation

import android.os.Bundle
import br.com.alex.imdbstudycase.core.presentation.BaseActivity
import br.com.alex.imdbstudycase.home.R
import br.com.alex.imdbstudycase.home.di.HomeModule
import org.koin.core.context.loadKoinModules

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val modules = listOf(
            HomeModule.homeViewModelModule,
            HomeModule.homeUseCaseModule,
            HomeModule.homeRepositoryModule,
            HomeModule.homeApiModule
        )

        loadKoinModules(modules)
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
}