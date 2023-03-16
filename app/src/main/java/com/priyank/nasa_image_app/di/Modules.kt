package com.priyank.nasa_image_app.di

import com.priyank.nasa_image_app.data.remote.ImagesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class Modules {

    @Provides
    fun retrofitInstance(): ImagesApi {
        return Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/").addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(
            ImagesApi::class.java
        )
    }
}
