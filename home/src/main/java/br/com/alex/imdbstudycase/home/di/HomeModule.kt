package br.com.alex.imdbstudycase.home.di

import br.com.alex.imdbstudycase.home.data.network.HomeApi
import br.com.alex.imdbstudycase.home.data.repository.HomeRepository
import br.com.alex.imdbstudycase.home.data.repository.HomeRepositoryImpl
import br.com.alex.imdbstudycase.home.domain.HomeUseCase
import br.com.alex.imdbstudycase.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object HomeModule {

    val instance = module {

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