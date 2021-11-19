package br.com.alex.imdbstudycase.moviedetails.data.repository

import br.com.alex.imdbstudycase.core.data.api.AppResult
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieDetails

interface MovieDetailsRepository {

    suspend fun getMovieDetails(): AppResult<MovieDetails>
}