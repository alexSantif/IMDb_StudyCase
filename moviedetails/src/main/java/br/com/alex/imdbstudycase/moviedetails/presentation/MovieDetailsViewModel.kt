package br.com.alex.imdbstudycase.moviedetails.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alex.imdbstudycase.core.data.api.AppResult
import br.com.alex.imdbstudycase.core.data.api.SingleLiveEvent
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieDetails
import br.com.alex.imdbstudycase.moviedetails.domain.MovieDetailsUseCase
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val useCase: MovieDetailsUseCase) : ViewModel() {

    val movieDetails = MutableLiveData<MovieDetails?>()

    private val showError = SingleLiveEvent<String>()

    fun getMovieDetails(movieId: String?) {
        viewModelScope.launch {
            when (val moviesResult = useCase.getMovieDetails(movieId)) {
                is AppResult.Success -> {
                    movieDetails.value = moviesResult.successData
                    showError.value = null
                }
                is AppResult.Error -> showError.value = moviesResult.exception.message
                else -> {
                }
            }
        }
    }
}