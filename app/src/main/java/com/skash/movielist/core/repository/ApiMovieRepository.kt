package com.skash.movielist.core.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.skash.movielist.core.model.Movie
import com.skash.movielist.core.network.api.MovieApi
import com.skash.movielist.core.paging.MoviePagingSource
import com.skash.movielist.core.paging.PagingConstants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApiMovieRepository @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {

    override fun fetchMoviesForFilterType(type: Movie.FilterType): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = PagingConstants.PAGE_SIZE
            ),
            pagingSourceFactory = {
                MoviePagingSource(movieApi, type)
            }).flow
    }
}