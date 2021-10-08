package br.com.alex.imdbstudycase.home.data.repository

import br.com.alex.imdbstudycase.core.data.api.AppResult
import br.com.alex.imdbstudycase.core.data.api.handleApiError
import br.com.alex.imdbstudycase.core.data.api.handleSuccess
import br.com.alex.imdbstudycase.home.data.model.MoviesResponse
import br.com.alex.imdbstudycase.home.data.network.HomeApi

class HomeRepositoryImpl(private val api: HomeApi) : HomeRepository {

    override suspend fun getMovies(): AppResult<MoviesResponse> {
        return try {
            val response = api.getMovies()
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