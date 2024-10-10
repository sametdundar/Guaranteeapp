package com.sametdundar.guaranteeapp.navigation

import com.sametdundar.guaranteeapp.R

sealed class BottomNavItem(
    val title: String,
    val image: Int,
    val route: String
) {
    object Home : BottomNavItem(
        title = "List",
        image = R.drawable.ic_home,
        route = ScreenRoutes.Home.route
    )

    object Favorites : BottomNavItem(
        title = "Share",
        image = R.drawable.ic_favorites,
        route = ScreenRoutes.Favorites.route
    )

    object Messages : BottomNavItem(
        title = "Continue",
        image = R.drawable.ic_messages,
        route = ScreenRoutes.Messages.route
    )
}