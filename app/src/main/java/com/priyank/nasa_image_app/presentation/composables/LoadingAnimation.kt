package com.priyank.nasa_image_app.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.priyank.nasa_image_app.R

@Composable
fun Loading() {
    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.loading)
    )
    val progress by animateLottieCompositionAsState(
        composition,

        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = .5f,
        ignoreSystemAnimatorScale = true

    )
    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = Modifier.fillMaxSize()
    )
}

@Preview
@Composable
fun greeting() {
    Loading()
}
