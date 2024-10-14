package com.sametdundar.guaranteeapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.sametdundar.guaranteeapp.roomdatabase.FormData
import com.sametdundar.guaranteeapp.ui.screens.ContinueScreen
import com.sametdundar.guaranteeapp.ui.screens.ListScreen
import com.sametdundar.guaranteeapp.ui.screens.ShareScreen
import com.sametdundar.guaranteeapp.ui.screens.UserDetailsScreen
import com.sametdundar.guaranteeapp.utils.JsonConverter.fromJson
import com.sametdundar.guaranteeapp.utils.getParcelableData

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    bottomBarState: MutableState<Boolean>
) {

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.Home.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        composable(route = ScreenRoutes.Home.route) {
            bottomBarState.value = true
            ListScreen(navController = navController)
        }

        composable(route = ScreenRoutes.Favorites.route) {
            bottomBarState.value = true
            ShareScreen(navController = navController)
        }

        composable(route = ScreenRoutes.Messages.route) {
            bottomBarState.value = true
            ContinueScreen()
        }

        composable(route = ScreenRoutes.FormDetail.route+"?formData={formData}") { backStackEntry ->
            bottomBarState.value = false
            val formData =  backStackEntry.arguments?.getString("formData")
            val form = fromJson<FormData>(formData?:"")
            UserDetailsScreen(formData = form, navController = navController)

        }


    }
}