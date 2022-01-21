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

    val instance = module {

        viewModel {
            MovieDetailsViewModel(get())
        }

        factory {
            MovieDetailsUseCase(get())
        }

        factory<MovieDetailsRepository> {
            MovieDetailsRepositoryImpl(get(), get())
        }

        fun providesApi(retrofit: Retrofit): MovieDetailsApi {
            return retrofit.create(MovieDetailsApi::class.java)
        }
        factory { providesApi(get()) }
    }
}