package com.priyank.nasa_image_app.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.priyank.nasa_image_app.MainViewModel
import com.priyank.nasa_image_app.presentation.composables.imageItem

@OptIn(ExperimentalFoundationApi::class)
@Composable

fun imagesGridScreen(vm: MainViewModel = hiltViewModel(), navHostController: NavHostController) {

    if (vm.state.value.loading) {
        Text(text = "Loading")
    } else {

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(180.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(2.dp), horizontalArrangement = Arrangement.spacedBy(1.dp)
        ) {
            items(items = vm.state.value.images!!) {
                imageItem(it, vm, navHostController)
            }
        }
    }
}
