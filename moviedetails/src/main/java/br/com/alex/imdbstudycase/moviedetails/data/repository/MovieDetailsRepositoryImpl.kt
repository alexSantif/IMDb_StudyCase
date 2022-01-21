package br.com.alex.imdbstudycase.moviedetails.data.repository

import androidx.lifecycle.LiveData
import br.com.alex.imdbstudycase.core.data.api.AppResult
import br.com.alex.imdbstudycase.core.data.api.handleApiError
import br.com.alex.imdbstudycase.core.data.api.handleSuccess
import br.com.alex.imdbstudycase.core.data.db.MovieDao
import br.com.alex.imdbstudycase.core.data.db.MovieEntity
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieDetails
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieImages
import br.com.alex.imdbstudycase.moviedetails.data.network.MovieDetailsApi

class MovieDetailsRepositoryImpl(
    private val api: MovieDetailsApi,
    private val movieDao: MovieDao
) : MovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: String?): AppResult<MovieDetails> {
        return try {
            val response = api.getMovieDetails(movieId)
            if (response.isSuccessful) {
                handleSuccess(response)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }

    override suspend fun getMovieImages(movieId: String?): AppResult<MovieImages> {
        return try {
            val response = api.getMovieImages(movieId)
            if (response.isSuccessful) {
                handleSuccess(response)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }

    override suspend fun validateIsMovieFavorite(movieId: String?): LiveData<List<MovieEntity>> {
        val retorno = movieDao.getAllMovies()
        return retorno
    }

    override suspend fun addMovie(movieEntity: MovieEntity?) {
        movieEntity?.let {
            movieDao.addMovie(it)
        }
    }

    override suspend fun deleteMovie(movieEntity: MovieEntity?) {
        movieEntity?.let {
            movieDao.deleteMovie(it)
        }
    }
}