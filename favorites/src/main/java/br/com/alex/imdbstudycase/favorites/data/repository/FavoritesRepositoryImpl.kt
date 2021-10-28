package br.com.alex.imdbstudycase.favorites.data.repository

import br.com.alex.imdbstudycase.favorites.data.network.FavoritesApi

class FavoritesRepositoryImpl(private val api: FavoritesApi) : FavoritesRepository {

//    override suspend fun getMovies(): AppResult<MoviesResponse> {
//        return try {
//            val response = api.getMovies()
//            if (response.isSuccessful) {
//                handleSuccess(response)
//            } else {
//                handleApiError(response)
//            }
//        } catch (e: Exception) {
//            AppResult.Error(e)
//        }
//    }
}