package com.skash.movielist.core.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skash.movielist.BuildConfig
import com.skash.movielist.core.model.Movie
import com.skash.movielist.core.network.api.MovieApi
import com.skash.movielist.core.network.model.ApiMovieList
import com.skash.movielist.core.paging.PagingConstants.PAGING_STARTING_PAGE_INDEX

class MoviePagingSource(
    private val movieApi: MovieApi,
    private val filterType: Movie.FilterType
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: PAGING_STARTING_PAGE_INDEX

        return try {
            val response = createRequest(position, params.loadSize)

            LoadResult.Page(
                data = response.results.map { Movie(it) },
                prevKey = if (position == PAGING_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.results.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun createRequest(position: Int, pageSize: Int): ApiMovieList {
        return when (filterType) {
            Movie.FilterType.Popular -> {
                movieApi.getPopularMovies(BuildConfig.API_KEY, position, pageSize)
            }

            is Movie.FilterType.Recommendations -> {
                movieApi.getMovieRecommendations(filterType.movieId, position, pageSize)
            }

            is Movie.FilterType.Similar -> {
                movieApi.getSimilarMovies(filterType.movieId, position, pageSize)
            }

            Movie.FilterType.Trending -> {
                movieApi.getTrendingMovies(BuildConfig.API_KEY, position, pageSize)
            }

            Movie.FilterType.Upcoming -> {
                movieApi.getUpcomingMovies(BuildConfig.API_KEY, position, pageSize)
            }

            Movie.FilterType.NowPlaying -> {
                movieApi.getNowPlayingMovies(BuildConfig.API_KEY, position, pageSize)
            }

            Movie.FilterType.TopRated -> {
                movieApi.getTopRatedMovies(BuildConfig.API_KEY, position, pageSize)
            }

            is Movie.FilterType.Query -> {
                movieApi.searchMovies(
                    BuildConfig.API_KEY,
                    position,
                    pageSize,
                    filterType.query
                )
            }
        }
    }
}