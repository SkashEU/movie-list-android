package com.skash.movielist.core.network.model

import com.google.gson.annotations.SerializedName

data class ApiMovieList(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<ApiMovie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)