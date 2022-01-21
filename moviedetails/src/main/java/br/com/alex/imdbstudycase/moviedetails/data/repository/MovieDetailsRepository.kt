package br.com.alex.imdbstudycase.moviedetails.data.repository

import androidx.lifecycle.LiveData
import br.com.alex.imdbstudycase.core.data.api.AppResult
import br.com.alex.imdbstudycase.core.data.db.MovieEntity
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieDetails
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieImages

interface MovieDetailsRepository {

    suspend fun getMovieDetails(movieId: String?): AppResult<MovieDetails>

    suspend fun getMovieImages(movieId: String?): AppResult<MovieImages>

    suspend fun validateIsMovieFavorite(movieId: String?): LiveData<List<MovieEntity>>

    suspend fun addMovie(movieEntity: MovieEntity?)

    suspend fun deleteMovie(movieEntity: MovieEntity?)
}