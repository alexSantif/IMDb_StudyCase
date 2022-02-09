package br.com.alex.imdbstudycase.home.data.network

import br.com.alex.imdbstudycase.core.utils.Constants.IMDB_USER_KEY
import br.com.alex.imdbstudycase.home.data.model.MoviesResponse
import br.com.alex.imdbstudycase.home.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {

    @GET("$POPULAR_MOVIES$IMDB_USER_KEY")
    suspend fun getMovies(): Response<MoviesResponse>

    @GET("$SEARCH_MOVIE$IMDB_USER_KEY/{$SEARCH_TEXT}")
    suspend fun getSearchMovie(@Path(SEARCH_TEXT) text: String?): Response<SearchResponse>

    companion object {

        private const val POPULAR_MOVIES = "mostpopularmovies/"
        private const val SEARCH_MOVIE = "searchmovie/"
        private const val SEARCH_TEXT = "text"
    }
}