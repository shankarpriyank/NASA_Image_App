package com.priyank.nasa_image_app.di

import android.app.Application
import androidx.room.Room
import com.priyank.nasa_image_app.data.local.ImageDatabase
import com.priyank.nasa_image_app.data.remote.ImagesApi
import com.priyank.nasa_image_app.data.repository.ImageRepositoryImplementation
import com.priyank.nasa_image_app.domain.repository.ImageRepository
import com.priyank.nasa_image_app.util.ConnectivityObserver
import com.priyank.nasa_image_app.util.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    @Singleton
    fun retrofitInstance(): ImagesApi {
        return Retrofit.Builder().baseUrl("https://raw.githubusercontent.com/").addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(
            ImagesApi::class.java
        )
    }

    @Provides
    @Singleton
    fun provideImageDatabase(app: Application): ImageDatabase {
        return Room.databaseBuilder(
            app, ImageDatabase::class.java, "image_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideImageRepository(imagesApi: ImagesApi, db: ImageDatabase): ImageRepository {
        return ImageRepositoryImplementation(db.imageDao, imagesApi)
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivityObserver(context: Application): ConnectivityObserver {
        return NetworkConnectivityObserver(context.applicationContext)
    }
}
