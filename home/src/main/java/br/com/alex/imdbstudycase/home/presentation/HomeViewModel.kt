package br.com.alex.imdbstudycase.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alex.imdbstudycase.core.data.api.AppResult
import br.com.alex.imdbstudycase.core.data.api.SingleLiveEvent
import br.com.alex.imdbstudycase.core.navigation.MutableLiveEvent
import br.com.alex.imdbstudycase.home.data.model.MoviesResponse
import br.com.alex.imdbstudycase.home.data.model.SearchResponse
import br.com.alex.imdbstudycase.home.domain.HomeUseCase
import br.com.alex.imdbstudycase.home.navigation.HomeDirections
import br.com.alex.imdbstudycase.router.NavigableViewModel
import br.com.alex.imdbstudycase.router.model.NavigationCommand
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: HomeUseCase) : ViewModel(),
    NavigableViewModel<HomeDirections> {

    override val router = MutableLiveEvent<NavigationCommand>()

    val movies = MutableLiveData<MoviesResponse?>()

    val searchMovie = MutableLiveData<SearchResponse?>()

    private val showError = SingleLiveEvent<String>()

    fun getMovies() {
        viewModelScope.launch {
            when (val moviesResult = useCase.getMovies()) {
                is AppResult.Success -> {
                    movies.value = moviesResult.successData
                    showError.value = null
                }
                is AppResult.Error -> showError.value = moviesResult.exception.message
                else -> {
                }
            }
        }
    }

    fun navigateTo() {
        router.navigateTo(HomeDirections.FeatureActivity("1234"))
    }

    fun getSearchMovie(text: String?) {
        viewModelScope.launch {
            when (val searchMovieResult = useCase.getSearchMovie(text)) {
                is AppResult.Success -> {
                    searchMovie.value = searchMovieResult.successData
                    showError.value = null
                }
                is AppResult.Error -> showError.value = searchMovieResult.exception.message
                else -> {
                }
            }
        }
    }
}