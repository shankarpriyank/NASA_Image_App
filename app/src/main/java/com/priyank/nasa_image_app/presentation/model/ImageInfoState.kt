package com.priyank.nasa_image_app.presentation.model

import com.priyank.nasa_image_app.data.model.ImageInfo

data class ImageInfoState(
    val images: List<ImageInfo> = emptyList(),
    val loading: Boolean = true
)
