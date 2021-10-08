package br.com.alex.imdbstudycase.home.data.network

import br.com.alex.imdbstudycase.home.data.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {

    @GET("MostPopularMovies/k_dgbrd05s")
    suspend fun getMovies(): Response<MoviesResponse>

    companion object {

        private const val MOVIES = "/movies"
    }
}