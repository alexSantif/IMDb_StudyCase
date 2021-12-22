package br.com.alex.imdbstudycase.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alex.imdbstudycase.core.data.api.AppResult
import br.com.alex.imdbstudycase.core.data.api.SingleLiveEvent
import br.com.alex.imdbstudycase.home.data.model.MovieModelData
import br.com.alex.imdbstudycase.home.data.model.MoviesResponse
import br.com.alex.imdbstudycase.home.data.model.SearchResponse
import br.com.alex.imdbstudycase.home.domain.HomeUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: HomeUseCase) : ViewModel() {

    val movies = MutableLiveData<List<MovieModelData>?>()

    val searchMovie = MutableLiveData<List<MovieModelData>?>()

    private val showError = SingleLiveEvent<String>()

    fun getMovies() {
        viewModelScope.launch {
//            when (val moviesResult = useCase.getMovies()) {
//                is AppResult.Success -> {
//                    movies.value = moviesResult.successData
//                    showError.value = null
//                }
//                is AppResult.Error -> showError.value = moviesResult.exception.message
//                else -> {}
//            }
            val moviesResult = useCase.getMovies()
            moviesResult?.data?.let {
                movies.value = it
                showError.value = null
            } ?: let {
                moviesResult?.error?.let { error ->
                    showError.value = error
                }
            }
        }
    }

    fun getSearchMovie(text: String?) {
        viewModelScope.launch {
//            when (val searchMovieResult = useCase.getSearchMovie(text)) {
//                is AppResult.Success -> {
//                    searchMovie.value = searchMovieResult.successData
//                    showError.value = null
//                }
//                is AppResult.Error -> showError.value = searchMovieResult.exception.message
//                else -> {}
//            }
            val moviesResult = useCase.getSearchMovie(text)
            moviesResult?.data?.let {
                movies.value = it
                showError.value = null
            } ?: let {
                moviesResult?.error?.let { error ->
                    showError.value = error
                }
            }
        }
    }
}