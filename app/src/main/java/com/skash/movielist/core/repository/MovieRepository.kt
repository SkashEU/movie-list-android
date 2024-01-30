package com.skash.movielist.core.repository

import androidx.paging.PagingData
import com.skash.movielist.core.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun fetchMoviesForFilterType(type: Movie.FilterType): Flow<PagingData<Movie>>
}