package br.com.alex.imdbstudycase.home.di

import br.com.alex.imdbstudycase.home.data.network.HomeApi
import br.com.alex.imdbstudycase.home.data.repository.HomeRepository
import br.com.alex.imdbstudycase.home.data.repository.HomeRepositoryImpl
import br.com.alex.imdbstudycase.home.domain.HomeUseCase
import br.com.alex.imdbstudycase.home.navigation.HomeActivityDirectionImpl
import br.com.alex.imdbstudycase.home.presentation.HomeViewModel
import br.com.alex.imdbstudycase.router.direction.screen.HomeActivityDirection
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object HomeModule {

    val instance = module {

        factory<HomeActivityDirection> { (params: HomeActivityDirection.Params) ->
            HomeActivityDirectionImpl(
                context = androidContext(),
                params = params
            )
        }

        viewModel {
            HomeViewModel(get())
        }

        factory {
            HomeUseCase(get())
        }

        factory<HomeRepository> {
            HomeRepositoryImpl(get())
        }

        fun providesApi(retrofit: Retrofit): HomeApi {
            return retrofit.create(HomeApi::class.java)
        }
        factory { providesApi(get()) }
    }
}