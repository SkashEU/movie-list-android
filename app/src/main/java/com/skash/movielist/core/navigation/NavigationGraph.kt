package com.skash.movielist.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.skash.movielist.feature.movie.MovieScreen
import com.skash.movielist.feature.people.PeopleScreen
import com.skash.movielist.feature.show.TvShowScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationAction.Movies.route) {
        composable(NavigationAction.Movies.route) {
            MovieScreen()
        }
        composable(NavigationAction.People.route) {
            PeopleScreen()
        }
        composable(NavigationAction.TvShows.route) {
            TvShowScreen()
        }
    }
}