package com.priyank.nasa_image_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.priyank.nasa_image_app.navigation.SetupNavGraph
import com.priyank.nasa_image_app.presentation.ui.theme.NASA_Image_AppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val vm: MainViewModel by viewModels()
    private var keepOn = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {

            setKeepOnScreenCondition {
                GlobalScope.launch {
                    delay(2000)
                    keepOn = false
                }
                keepOn
                // Todo Figure Out why this call fucksup the repository
                //   vm.imageList.value
            }
        }
        setContent {
            NASA_Image_AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val navController = rememberNavController()
                    SetupNavGraph(
                        navController = navController,
                        startDestination = "imageGrid"
                    )
                }
            }
        }
    }
}
