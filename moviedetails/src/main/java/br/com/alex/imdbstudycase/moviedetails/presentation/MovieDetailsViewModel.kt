package br.com.alex.imdbstudycase.moviedetails.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.alex.imdbstudycase.core.data.api.AppResult
import br.com.alex.imdbstudycase.core.data.api.SingleLiveEvent
import br.com.alex.imdbstudycase.core.data.db.MovieEntity
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieDetails
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieImages
import br.com.alex.imdbstudycase.moviedetails.domain.MovieDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val useCase: MovieDetailsUseCase) : ViewModel() {

    val movieDetails = MutableLiveData<MovieDetails?>()

    val movieImages = MutableLiveData<MovieImages?>()

    val favoriteMovie = MutableLiveData<List<MovieEntity?>>()

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

    fun validateIsMovieFavorite(movieId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.validateIsMovieFavorite(movieId).let {
                favoriteMovie.postValue(it.value)
            }
        }
    }

    fun addMovie(movieEntity: MovieEntity?) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.addMovie(movieEntity)
        }
    }

    fun deleteMovie(movieEntity: MovieEntity?) {
        viewModelScope.launch(Dispatchers.IO) {
            useCase.deleteMovie(movieEntity)
        }
    }
}