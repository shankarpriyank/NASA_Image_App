package com.priyank.nasa_image_app.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.priyank.nasa_image_app.MainViewModel
import com.priyank.nasa_image_app.data.model.ImageInfo

@Composable
fun imageItem(image: ImageInfo, vm: MainViewModel, navHostController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { openImage(image, vm, navHostController) }
    ) {
        AsyncImage(
            model = image.url, contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
                .clip(
                    RoundedCornerShape(6.dp)
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent, Color.Black
                        ),
                        startY = .3f
                    )
                )
        )
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Text(text = image.title.toString()!!, style = TextStyle(color = Color.White, fontSize = 16.sp), modifier = Modifier.padding(6.dp))
        }
    }
}
fun openImage(image: ImageInfo, vm: MainViewModel, navHostController: NavHostController) {
    val imageList = vm.state.value.images!!
    val index = imageList!!.indexOf(image)
    vm.openImageDetailScreen(navHostController = navHostController, id = index)
}
