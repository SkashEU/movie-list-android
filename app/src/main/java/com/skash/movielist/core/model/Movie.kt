package com.skash.movielist.core.model

import com.skash.movielist.BuildConfig
import com.skash.movielist.core.network.model.ApiMovie

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val imageURL: String,
    val releaseDate: String
) {
    constructor(apiModel: ApiMovie) : this(
        id = apiModel.id,
        title = apiModel.title,
        overview = apiModel.overview,
        imageURL = "${BuildConfig.IMAGE_BASE_URL}${apiModel.posterPath}",
        releaseDate = apiModel.releaseDate
    )

    sealed class FilterType {
        class Recommendations(val movieId: Int) : FilterType()
        class Similar(val movieId: Int) : FilterType()
        class Query(val query: String) : FilterType()
        data object Popular : FilterType()
        data object Trending : FilterType()
        data object Upcoming : FilterType()
        data object NowPlaying : FilterType()
        data object TopRated : FilterType()
    }
}