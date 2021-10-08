package br.com.alex.imdbstudycase

import android.app.Application
import br.com.alex.imdbstudycase.core.di.CoreModule.networkModule
import br.com.alex.imdbstudycase.home.di.HomeModule.homeApiModule
import br.com.alex.imdbstudycase.home.di.HomeModule.homeUseCaseModule
import br.com.alex.imdbstudycase.home.di.HomeModule.homeViewModelModule
import br.com.alex.imdbstudycase.home.di.HomeModule.homeRepositoryModule
import br.com.alex.imdbstudycase.router.di.RouterModule.routerModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(
                routerModule,
                homeViewModelModule,
                homeUseCaseModule,
                homeRepositoryModule,
                homeApiModule,
                networkModule
            )
        }
    }
}