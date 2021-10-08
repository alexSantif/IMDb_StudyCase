package br.com.alex.imdbstudycase.home.domain

import br.com.alex.imdbstudycase.home.data.repository.HomeRepository
import br.com.alex.imdbstudycase.core.data.api.AppResult
import br.com.alex.imdbstudycase.home.data.model.MoviesResponse

class HomeUseCase(private val repository: HomeRepository) {

    suspend fun getMovies(): AppResult<MoviesResponse> {
        return repository.getMovies()
    }
}