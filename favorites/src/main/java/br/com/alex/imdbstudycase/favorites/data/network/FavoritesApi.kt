package br.com.alex.imdbstudycase.favorites.data.network

import retrofit2.Response
import retrofit2.http.GET

interface FavoritesApi {

//    @GET("MostPopularMovies/k_dgbrd05s")
//    suspend fun getMovies(): Response<MoviesResponse>

    companion object {

        private const val MOVIES = "/movies"
    }
}