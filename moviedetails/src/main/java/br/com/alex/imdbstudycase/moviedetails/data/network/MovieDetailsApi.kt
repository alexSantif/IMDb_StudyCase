package br.com.alex.imdbstudycase.moviedetails.data.network

import br.com.alex.imdbstudycase.moviedetails.data.model.MovieDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailsApi {

    @GET("Title/k_dgbrd05s/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: String?): Response<MovieDetails>
}