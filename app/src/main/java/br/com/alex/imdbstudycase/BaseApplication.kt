package br.com.alex.imdbstudycase

import android.app.Application
import br.com.alex.imdbstudycase.core.di.CoreModule
import br.com.alex.imdbstudycase.home.di.HomeModule
import br.com.alex.imdbstudycase.moviedetails.di.MovieDetailsModule
import br.com.alex.imdbstudycase.router.di.RouterModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                CoreModule.instance,
                HomeModule.instance,
                MovieDetailsModule.instance,
                RouterModule.instance
            )
        }
    }
}