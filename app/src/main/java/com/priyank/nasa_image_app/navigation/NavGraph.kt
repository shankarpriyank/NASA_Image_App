package com.priyank.nasa_image_app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.priyank.nasa_image_app.MainViewModel
import com.priyank.nasa_image_app.presentation.ImageDetailScreen
import com.priyank.nasa_image_app.presentation.ImagesGridScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        navigation(
            startDestination = Screen.ImageGrid.route, route = "complete_nav_graph"
        ) {

            composable(route = Screen.ImageGrid.route) { entry ->
                ImagesGridScreen(
                    vm = entry.sharedViewModel<MainViewModel>(navController = navController),
                    navHostController = navController
                )
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
            ) { entry ->
                ImageDetailScreen(
                    vm = entry.sharedViewModel<MainViewModel>(navController = navController),
                    navHostController = navController,
                    id = entry.arguments!!.getInt("id")
                )
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}
