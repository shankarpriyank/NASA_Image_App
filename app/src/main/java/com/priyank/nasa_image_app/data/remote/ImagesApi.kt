package com.priyank.nasa_image_app.data.remote

import com.priyank.nasa_image_app.data.ImageInfo
import retrofit2.http.GET

interface ImagesApi {

    @GET("obvious/take-home-exercise-data/trunk/nasa-pictures.json")
    suspend fun getImageData(): List<ImageInfo>
}
