package com.skash.movielist.core.helper

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.skash.movielist.core.navigation.NavigationAction

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: NavigationAction) =
    this?.hierarchy?.any {
        it.route?.contains(destination.route, true) ?: false
    } ?: false
