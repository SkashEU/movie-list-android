package com.skash.movielist.core.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.skash.movielist.R

sealed class NavigationAction(
    @StringRes var title: Int,
    @DrawableRes var icon: Int,
    var route: String
) {

    data object Movies : NavigationAction(
        R.string.title_movies,
        R.drawable.icn_movie,
        "route_movie"
    )

    data object People : NavigationAction(
        R.string.title_people,
        R.drawable.icn_person,
        "route_people"
    )

    data object TvShows : NavigationAction(
        R.string.title_tv_shows,
        R.drawable.icn_tv_show,
        "route_tv_shows"
    )


}