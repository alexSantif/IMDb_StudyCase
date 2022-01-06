package br.com.alex.imdbstudycase.moviedetails.data.network

import br.com.alex.imdbstudycase.core.utils.Constants.IMDB_USER_KEY
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieDetails
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieImages
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailsApi {

    @GET("$MOVIE_TITLE$IMDB_USER_KEY/{$MOVIE_ID}")
    suspend fun getMovieDetails(@Path(MOVIE_ID) movieId: String?): Response<MovieDetails>

    @GET("$MOVIE_IMAGES$IMDB_USER_KEY/{$MOVIE_ID}$SHORT_ITEMS")
    suspend fun getMovieImages(@Path(MOVIE_ID) movieId: String?): Response<MovieImages>

    companion object {

        private const val MOVIE_ID = "movieId"
        private const val MOVIE_TITLE = "Title/"
        private const val MOVIE_IMAGES = "Images/"
        private const val SHORT_ITEMS = "/Short"
    }
}