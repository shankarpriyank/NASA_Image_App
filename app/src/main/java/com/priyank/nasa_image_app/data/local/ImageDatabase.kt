package com.priyank.nasa_image_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.priyank.nasa_image_app.data.model.ImageInfo

@Database(entities = [ImageInfo::class], version = 0)
abstract class ImageDatabase : RoomDatabase() {
    abstract val dao: ImageDao
}
