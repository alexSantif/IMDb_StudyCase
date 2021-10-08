package br.com.alex.imdbstudycase.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alex.imdbstudycase.core.data.api.AppResult
import br.com.alex.imdbstudycase.core.data.api.SingleLiveEvent
import br.com.alex.imdbstudycase.home.data.model.MoviesResponse
import br.com.alex.imdbstudycase.home.domain.HomeUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: HomeUseCase) : ViewModel() {

    val movies = MutableLiveData<MoviesResponse?>()

    private val showError = SingleLiveEvent<String>()

    fun getMovies() {
        viewModelScope.launch {
            when (val moviesResult = useCase.getMovies()) {
                is AppResult.Success -> {
                    movies.value = moviesResult.successData
                    showError.value = null
                }
                is AppResult.Error -> showError.value = moviesResult.exception.message
                else -> {}
            }
        }
    }
}