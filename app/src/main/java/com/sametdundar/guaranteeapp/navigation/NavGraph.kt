package com.sametdundar.guaranteeapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sametdundar.guaranteeapp.ui.screens.ContinueScreen
import com.sametdundar.guaranteeapp.ui.screens.FavoritesScreen
import com.sametdundar.guaranteeapp.ui.screens.ShareScreen

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.Home.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable(route = ScreenRoutes.Home.route) {
            FavoritesScreen()
        }

        composable(route = ScreenRoutes.Favorites.route) {
            ShareScreen()
        }

        composable(route = ScreenRoutes.Messages.route) {
            ContinueScreen()
        }

    }
}