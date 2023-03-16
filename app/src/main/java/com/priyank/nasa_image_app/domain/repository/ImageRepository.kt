package com.priyank.nasa_image_app.domain.repository

import com.priyank.nasa_image_app.data.model.ImageInfo
import com.priyank.nasa_image_app.util.Resource
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    fun getImages(): Flow<Resource<List<ImageInfo>>>
}
