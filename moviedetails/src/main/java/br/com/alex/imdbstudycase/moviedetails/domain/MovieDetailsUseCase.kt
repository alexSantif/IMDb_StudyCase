package br.com.alex.imdbstudycase.moviedetails.domain

import br.com.alex.imdbstudycase.core.data.api.AppResult
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieDetails
import br.com.alex.imdbstudycase.moviedetails.data.repository.MovieDetailsRepository

class MovieDetailsUseCase(private val repository: MovieDetailsRepository) {

    suspend fun getMovieDetails(): AppResult<MovieDetails> {
        return repository.getMovieDetails()
    }
}