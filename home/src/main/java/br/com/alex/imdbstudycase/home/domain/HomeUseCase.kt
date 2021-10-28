package br.com.alex.imdbstudycase.home.domain

import br.com.alex.imdbstudycase.favorites.data.repository.FavoritesRepository
import br.com.alex.imdbstudycase.core.data.api.AppResult
import br.com.alex.imdbstudycase.home.data.model.MoviesResponse
import br.com.alex.imdbstudycase.home.data.repository.HomeRepository

class HomeUseCase(private val repository: HomeRepository) {

    suspend fun getMovies(): AppResult<MoviesResponse> {
        return repository.getMovies()
    }
}