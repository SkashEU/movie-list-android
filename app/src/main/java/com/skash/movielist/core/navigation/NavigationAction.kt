package com.skash.movielist.core.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

sealed class NavigationActions(
    @StringRes var title: Int,
    @DrawableRes var icon: Int,
    var route: String
) {


}