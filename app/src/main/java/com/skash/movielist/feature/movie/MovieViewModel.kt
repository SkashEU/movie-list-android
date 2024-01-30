package com.skash.movielist.feature.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.skash.movielist.core.model.Movie
import com.skash.movielist.core.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    private val _trendingMoviesLiveData = MutableLiveData<PagingData<Movie>>()
    val trendingMoviesLiveData: LiveData<PagingData<Movie>> get() = _trendingMoviesLiveData

    private val _popularMoviesLiveData = MutableLiveData<PagingData<Movie>>()
    val popularMoviesLiveData: LiveData<PagingData<Movie>> get() = _popularMoviesLiveData

    private val _upcomingMoviesLiveData = MutableLiveData<PagingData<Movie>>()
    val upcomingMoviesLiveData: LiveData<PagingData<Movie>> get() = _upcomingMoviesLiveData

    private val _nowPlayingMoviesLiveData = MutableLiveData<PagingData<Movie>>()
    val nowPlayingMoviesLiveData: LiveData<PagingData<Movie>> get() = _nowPlayingMoviesLiveData

    private val _topRatedMoviesLiveData = MutableLiveData<PagingData<Movie>>()
    val topRatedMoviesLiveData: LiveData<PagingData<Movie>> get() = _topRatedMoviesLiveData

    private val _queryResultLiveData = MutableLiveData<PagingData<Movie>>()
    val queryResultLiveData: LiveData<PagingData<Movie>> get() = _queryResultLiveData

    init {
        fetchTrendingMovies()
        fetchPopularMovies()
        fetchUpcomingMovies()
        fetchNowPlayingMovies()
        fetchTopRatedMovies()
    }

    private fun fetchTrendingMovies() {
        movieRepository.fetchMoviesForFilterType(Movie.FilterType.Trending)
            .onEach {
                _trendingMoviesLiveData.postValue(it)
            }
            .launchIn(viewModelScope)
    }

    private fun fetchPopularMovies() {
        movieRepository.fetchMoviesForFilterType(Movie.FilterType.Popular)
            .onEach {
                _popularMoviesLiveData.postValue(it)
            }
            .launchIn(viewModelScope)
    }

    private fun fetchUpcomingMovies() {
        movieRepository.fetchMoviesForFilterType(Movie.FilterType.Upcoming)
            .onEach {
                _upcomingMoviesLiveData.postValue(it)
            }
            .launchIn(viewModelScope)
    }

    private fun fetchNowPlayingMovies() {
        movieRepository.fetchMoviesForFilterType(Movie.FilterType.NowPlaying)
            .onEach {
                _nowPlayingMoviesLiveData.postValue(it)
            }
            .launchIn(viewModelScope)
    }

    private fun fetchTopRatedMovies() {
        movieRepository.fetchMoviesForFilterType(Movie.FilterType.TopRated)
            .onEach {
                _topRatedMoviesLiveData.postValue(it)
            }
            .launchIn(viewModelScope)
    }
}