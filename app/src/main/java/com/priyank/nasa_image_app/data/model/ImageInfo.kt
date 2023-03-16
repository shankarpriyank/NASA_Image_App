package com.priyank.nasa_image_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ImageTable")
data class ImageInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val copyright: String?,
    val date: String?,
    val explanation: String?,
    val hdurl: String?,
    val media_type: String?,
    val service_version: String?,
    val title: String?,
    val url: String?
)
