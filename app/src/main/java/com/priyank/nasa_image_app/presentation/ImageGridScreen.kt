package com.priyank.nasa_image_app.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.priyank.nasa_image_app.MainViewModel
import com.priyank.nasa_image_app.presentation.composables.imageItem

@Composable

fun imagesGridScreen(vm: MainViewModel = hiltViewModel(), navHostController: NavHostController) {

    if (vm.isloading.collectAsState().value) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Loading", fontWeight = FontWeight.Bold, fontSize = 100.sp)

            // Todo Figure Out Why this lottie animation is not visible
            //  Loading()
        }
    } else {
        imageGrid(vm, navHostController)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun imageGrid(vm: MainViewModel, navHostController: NavHostController) {
    val imageList = vm.imageList.collectAsState()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(180.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(2.dp), horizontalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        items(items = imageList.value) {
            imageItem(it, vm, navHostController)
        }
    }
}
