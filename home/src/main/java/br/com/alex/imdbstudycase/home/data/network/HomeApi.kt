package br.com.alex.imdbstudycase.home.data.network

import br.com.alex.imdbstudycase.home.data.model.MoviesResponse
import br.com.alex.imdbstudycase.home.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {

    @GET("MostPopularMovies/k_dgbrd05s")
    suspend fun getMovies(): Response<MoviesResponse>

    @GET("SearchMovie/k_dgbrd05s/{text}")
    suspend fun getSearchMovie(@Path("text") text: String?): Response<SearchResponse>
}