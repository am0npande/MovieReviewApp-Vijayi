package com.example.vijayiproject.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vijayiproject.data.dto.movieData
import com.example.vijayiproject.domain.MovieRepository
import com.example.vijayiproject.presentation.states.MovieUiState
import com.example.vijayiproject.utils.Resource
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MovieRepository
): ViewModel() {

    private val disposables = CompositeDisposable()

    private val _selectMovie = MutableStateFlow<movieData?>(null)
    val selectedMovie = _selectMovie.asStateFlow()

    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState = _uiState.asStateFlow()

//    init {
//        fetchMovies()
//    }

    fun setMovie(movie: movieData){
        viewModelScope.launch {
            _selectMovie.value = movie
        }
    }
    fun fetchMovies() {
        _uiState.update { it.copy(isLoading = true, error = "") }

        val upcomingObservable = repository.getMoviesUpComingList()
            .subscribeOn(Schedulers.io())

        val popularObservable = repository.getMoviesPopularList()
            .subscribeOn(Schedulers.io())

        Observable.zip(upcomingObservable, popularObservable) { upcoming, popular ->
            Pair(upcoming, popular)
        }
            .subscribeBy(
                onNext = { (upcoming, popular) ->
                    when {
                        upcoming is Resource.Loading || popular is Resource.Loading -> {
                            _uiState.update { it.copy(isLoading = true) }
                        }

                        upcoming is Resource.Success && popular is Resource.Success -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    upcomingMovies = upcoming.data ?: emptyList(),
                                    popularMovies = popular.data ?: emptyList(),
                                    error = ""
                                )
                            }
                        }

                        upcoming is Resource.Error || popular is Resource.Error -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    error = (upcoming as? Resource.Error)?.message
                                        ?: (popular as? Resource.Error)?.message
                                        ?: "Something went wrong"
                                )
                            }
                        }
                    }
                },
                onError = { throwable ->
                    _uiState.update {
                        it.copy(isLoading = false, error = throwable.message ?: "Unknown error")
                    }
                }
            ).addTo(disposables)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}