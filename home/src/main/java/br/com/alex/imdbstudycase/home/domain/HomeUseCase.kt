package br.com.alex.imdbstudycase.home.domain

import android.util.Log
import br.com.alex.imdbstudycase.core.data.api.AppResult
import br.com.alex.imdbstudycase.home.data.model.MoviesResponse
import br.com.alex.imdbstudycase.home.data.model.SearchResponse
import br.com.alex.imdbstudycase.home.data.repository.HomeRepository

class HomeUseCase(private val repository: HomeRepository) {

    suspend fun getMovies(): AppResult<MoviesResponse> = repository.getMovies()

    suspend fun getSearchMovie(text: String?): AppResult<SearchResponse> {
        Log.v("vamos ver", "vamos ver")
        return repository.getSearchMovie(text)
    }
}