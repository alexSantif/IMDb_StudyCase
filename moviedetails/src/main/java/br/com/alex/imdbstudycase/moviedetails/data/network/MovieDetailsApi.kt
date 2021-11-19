package br.com.alex.imdbstudycase.moviedetails.data.network

import br.com.alex.imdbstudycase.moviedetails.data.model.MovieDetails
import retrofit2.Response
import retrofit2.http.GET

interface MovieDetailsApi {

    @GET("Title/k_dgbrd05s/tt1375666")
    suspend fun getMovieDetails(): Response<MovieDetails>
}