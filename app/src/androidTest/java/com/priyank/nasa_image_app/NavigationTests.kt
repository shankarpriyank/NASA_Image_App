package com.priyank.nasa_image_app

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.testing.TestNavHostController
import com.priyank.nasa_image_app.navigation.Screen
import com.priyank.nasa_image_app.navigation.SetupNavGraph
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test

class NavigationTests {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testNavigation() {
        // Create a TestNavHostController
        val navController = TestNavHostController(composeTestRule.activity)
        composeTestRule.activity.setContent {
            SetupNavGraph(navController = navController, startDestination = Screen.ImageGrid.route)
        }
        // Check if the start destination is correct
        assertThat(navController.currentDestination?.route, `is`(Screen.ImageGrid.route))

        // Navigate to the ImageDetail screen
        composeTestRule.onNodeWithTag(Screen.ImageGrid.route)
            .performClick()
        composeTestRule.onNodeWithTag("image_1")
            .performClick()
        assertThat(navController.currentDestination?.route, `is`(Screen.ImageDetail.route + "/1"))

        // Navigate back to the ImageGrid screen
        navController.navigate(Screen.ImageGrid.route)
        assertThat(navController.currentDestination?.route, `is`(Screen.ImageDetail.route))
    }
}
