package com.priyank.nasa_image_app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.priyank.nasa_image_app.data.model.ImageInfo

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: ImageInfo)

    @Query("SELECT * FROM imageTable ORDER BY date ASC ")
    suspend fun getAllImages(): List<ImageInfo>

    @Query("DELETE FROM imagetable")
    fun clearTable()
}
