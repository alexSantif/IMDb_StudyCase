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

    val homeViewModelModule = module {
        viewModel { HomeViewModel(get()) }
    }

    val homeUseCaseModule = module {

        fun providesUseCase(repository: HomeRepository): HomeUseCase {
            return HomeUseCase(repository)
        }
        single { providesUseCase(get()) }
    }

    val homeRepositoryModule = module {

        fun providesRepository(api: HomeApi): HomeRepository {
            return HomeRepositoryImpl(api)
        }
        single { providesRepository(get()) }
    }

    val homeApiModule = module {

        fun providesApi(retrofit: Retrofit): HomeApi {
            return retrofit.create(HomeApi::class.java)
        }
        single { providesApi(get()) }

    }
}