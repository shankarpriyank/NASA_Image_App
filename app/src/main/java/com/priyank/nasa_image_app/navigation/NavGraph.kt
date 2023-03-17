package com.priyank.nasa_image_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.priyank.nasa_image_app.presentation.imageDetailScreen
import com.priyank.nasa_image_app.presentation.imagesGridScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.ImageGrid.route) {
            imagesGridScreen(navHostController = navController)
        }
        composable(
            route = Screen.ImageDetail.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                }
            )
        ) {
            imageDetailScreen(navHostController = navController, id = it.arguments!!.getInt("id"))
        }
    }
}
