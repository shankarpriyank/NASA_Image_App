package com.priyank.nasa_image_app.navigation

sealed class Screen(val route: String) {
    object ImageGrid : Screen(route = "imageGrid")
    object ImageDetail : Screen(route = "imageDetail")

    fun withArgs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}
