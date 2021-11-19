package br.com.alex.imdbstudycase.moviedetails.data.repository

import br.com.alex.imdbstudycase.core.data.api.AppResult
import br.com.alex.imdbstudycase.core.data.api.handleApiError
import br.com.alex.imdbstudycase.core.data.api.handleSuccess
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieDetails
import br.com.alex.imdbstudycase.moviedetails.data.network.MovieDetailsApi

class MovieDetailsRepositoryImpl(private val api: MovieDetailsApi) : MovieDetailsRepository {

    override suspend fun getMovieDetails(): AppResult<MovieDetails> {
        return try {
            val response = api.getMovieDetails()
            if (response.isSuccessful) {
                handleSuccess(response)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }
}