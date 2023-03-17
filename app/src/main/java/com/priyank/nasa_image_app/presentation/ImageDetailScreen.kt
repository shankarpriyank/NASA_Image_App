package com.priyank.nasa_image_app.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.priyank.nasa_image_app.MainViewModel
import com.priyank.nasa_image_app.presentation.composables.imageDetailItem
import kotlinx.coroutines.launch

@Composable
fun imageDetailScreen(
    vm: MainViewModel = hiltViewModel(),
    navHostController: NavHostController,
    id: Int
) {
    val listState = rememberLazyListState()

    // Remember a CoroutineScope to be able to launch
    val coroutineScope = rememberCoroutineScope()

    val imageList = vm.imageList.collectAsState()
    val isLoading = vm.isloading.collectAsState()
    val index = id
    LaunchedEffect(key1 = true) {

        coroutineScope.launch {
            listState.scrollToItem(index)
        }
    }

    if (isLoading.value) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Text(text = "Loading", fontWeight = FontWeight.Bold, fontSize = 100.sp)

            // Todo Figure Out Why this lottie animation is not visible
            //  Loading()
        }
    } else {

        Box(modifier = Modifier.fillMaxSize()) {
            Divider()
            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .align(Alignment.Center),
                state = listState
            ) {
                items(imageList.value) {
                    imageDetailItem(image = it, navHostController, vm)
                }
            }
        }
    }
}
