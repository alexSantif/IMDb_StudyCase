package br.com.alex.imdbstudycase.moviedetails.data.repository

import br.com.alex.imdbstudycase.core.data.api.AppResult
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieDetails
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieImages

interface MovieDetailsRepository {

    suspend fun getMovieDetails(movieId: String?): AppResult<MovieDetails>

    suspend fun getMovieImages(movieId: String?): AppResult<MovieImages>
}