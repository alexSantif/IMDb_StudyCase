package br.com.alex.imdbstudycase

import android.app.Application
import br.com.alex.imdbstudycase.core.di.CoreModule.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                networkModule
            )
        }
    }
}