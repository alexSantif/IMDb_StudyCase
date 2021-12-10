package br.com.alex.imdbstudycase.home.data.repository

import br.com.alex.imdbstudycase.core.data.api.AppResult
import br.com.alex.imdbstudycase.home.data.model.MoviesResponse
import br.com.alex.imdbstudycase.home.data.model.SearchResponse

interface HomeRepository {

    suspend fun getMovies(): AppResult<MoviesResponse>

    suspend fun getSearchMovie(text: String?): AppResult<SearchResponse>
}