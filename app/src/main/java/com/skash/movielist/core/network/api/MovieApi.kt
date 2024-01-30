package com.skash.movielist.core.network.api

import com.skash.movielist.core.network.model.ApiMovieList
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Header("Authorization") authorization: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ApiMovieList

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Header("Authorization") authorization: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ApiMovieList

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Header("Authorization") authorization: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ApiMovieList

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Header("Authorization") authorization: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ApiMovieList

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Header("Authorization") authorization: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ApiMovieList

    @GET("movie/{movieId}/recommendations")
    suspend fun getMovieRecommendations(
        @Path("movieId") movieId: Int,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ApiMovieList

    @GET("movie/{movieId}/similar")
    suspend fun getSimilarMovies(
        @Path("movieId") movieId: Int,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): ApiMovieList

    @GET("search/movie")
    suspend fun searchMovies(
        @Header("Authorization") authorization: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("query") query: String
    ): ApiMovieList
}