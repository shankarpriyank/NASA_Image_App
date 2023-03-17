package com.priyank.nasa_image_app

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.priyank.nasa_image_app.data.local.ImageDao
import com.priyank.nasa_image_app.data.local.ImageDatabase
import com.priyank.nasa_image_app.data.model.ImageInfo
import com.priyank.nasa_image_app.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ImageDaoTest {

    private lateinit var imageDao: ImageDao
    private lateinit var db: ImageDatabase

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ImageDatabase::class.java).build()
        imageDao = db.imageDao
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun insertImage_getAllImages() = runBlockingTest {
        // GIVEN
        val image = ImageInfo(
            id = 1,
            title = "Test Image",
            url = "https://example.com/image.jpg",
            date = "2022-03-17", hdurl = "", copyright = "", explanation = "", media_type = "", service_version = ""
        )

        // WHEN
        imageDao.insertImage(image)
        val result = imageDao.getAllImages()

        // THEN
        assertThat(result.size, `is`(1))
        assertThat(result[0].id, `is`(image.id))
        assertThat(result[0].title, `is`(image.title))
        assertThat(result[0].url, `is`(image.url))
        assertThat(result[0].date, `is`(image.date))
    }

    @Test
    fun clearTable_getAllImages() = runBlockingTest {
        // GIVEN
        val image = ImageInfo(
            id = 1,
            title = "Test Image",
            url = "https://example.com/image.jpg",
            date = "2022-03-17", copyright = "", explanation = "", hdurl ="" , media_type ="" , service_version = ""
        )
        imageDao.insertImage(image)

        // WHEN
        imageDao.clearTable()
        val result = imageDao.getAllImages()

        // THEN
        assertThat(result.size, `is`(0))
    }
}
