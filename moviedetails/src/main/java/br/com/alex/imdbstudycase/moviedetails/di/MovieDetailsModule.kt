package br.com.alex.imdbstudycase.moviedetails.di

import br.com.alex.imdbstudycase.moviedetails.data.network.MovieDetailsApi
import br.com.alex.imdbstudycase.moviedetails.data.repository.MovieDetailsRepository
import br.com.alex.imdbstudycase.moviedetails.data.repository.MovieDetailsRepositoryImpl
import br.com.alex.imdbstudycase.moviedetails.domain.MovieDetailsUseCase
import br.com.alex.imdbstudycase.moviedetails.presentation.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object MovieDetailsModule {

    val movieDetailsViewModelModule = module {
        viewModel { MovieDetailsViewModel(get()) }
    }

    val movieDetailsUseCaseModule = module {

        fun providesUseCase(repository: MovieDetailsRepository): MovieDetailsUseCase {
            return MovieDetailsUseCase(repository)
        }
        single { providesUseCase(get()) }
    }

    val movieDetailsRepositoryModule = module {

        fun providesRepository(api: MovieDetailsApi): MovieDetailsRepository {
            return MovieDetailsRepositoryImpl(api)
        }
        single { providesRepository(get()) }
    }

    val movieDetailsApiModule = module {

        fun providesApi(retrofit: Retrofit): MovieDetailsApi {
            return retrofit.create(MovieDetailsApi::class.java)
        }
        single { providesApi(get()) }

    }
}