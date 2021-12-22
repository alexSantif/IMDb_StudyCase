package br.com.alex.imdbstudycase.moviedetails.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alex.imdbstudycase.core.data.api.AppResult
import br.com.alex.imdbstudycase.core.data.api.SingleLiveEvent
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieDetails
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieImages
import br.com.alex.imdbstudycase.moviedetails.domain.MovieDetailsUseCase
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val useCase: MovieDetailsUseCase) : ViewModel() {

    val movieDetails = MutableLiveData<MovieDetails?>()

    val movieImages = MutableLiveData<MovieImages?>()

    private val showError = SingleLiveEvent<String>()

    fun getMovieDetails(movieId: String?) {
        viewModelScope.launch {
            when (val moviesResult = useCase.getMovieDetails(movieId)) {
                is AppResult.Success -> {
                    movieDetails.value = moviesResult.successData
                }
                is AppResult.Error -> showError.value = moviesResult.exception.message
                else -> {
                }
            }
        }
    }

    fun getMovieImages(movieId: String?) {
        viewModelScope.launch {
            when (val moviesResult = useCase.getMovieImages(movieId)) {
                is AppResult.Success -> {
                    movieImages.value = moviesResult.successData
                }
                is AppResult.Error -> showError.value = moviesResult.exception.message
                else -> {
                }
            }
        }
    }
}