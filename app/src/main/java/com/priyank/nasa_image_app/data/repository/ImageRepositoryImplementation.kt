package com.priyank.nasa_image_app.data.repository

import com.priyank.nasa_image_app.data.local.ImageDao
import com.priyank.nasa_image_app.data.model.ImageInfo
import com.priyank.nasa_image_app.data.remote.ImagesApi
import com.priyank.nasa_image_app.domain.repository.ImageRepository
import com.priyank.nasa_image_app.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class ImageRepositoryImplementation(
    private val ImageDao: ImageDao,
    private val ImagesAPI: ImagesApi
) : ImageRepository {

    // Todo: Handle errors in a better way

    override fun getImages(): Flow<Resource<List<ImageInfo>>> {

        return flow {

            emit(Resource.Loading())
            lateinit var imagesFromLocalDB: List<ImageInfo>
            lateinit var images: List<ImageInfo>
            lateinit var newlyCachedImages: List<ImageInfo>

            withContext(Dispatchers.IO) {
                imagesFromLocalDB = ImageDao.getAllImages()
            }
            emit(Resource.Loading(data = imagesFromLocalDB))
            try {
                withContext(Dispatchers.IO) {
                    images = ImagesAPI.getImages()
                }
                withContext(Dispatchers.IO) {
                    ImageDao.clearTable()
                }

                withContext(Dispatchers.IO) {
                    for (image in images) {
                        ImageDao.insertImage(image)
                    }
                    newlyCachedImages = ImageDao.getAllImages()
                }

                emit(Resource.Success(data = newlyCachedImages))
            } catch (e: java.net.UnknownHostException) {
                emit(
                    Resource.Error(
                        message = "Please Check Your Internet Connection"
                    )
                )
            } catch (e: Exception) {
                Resource.Error(
                    message = "Something Went Wrong",
                    data = null

                )
            } catch (e: java.net.SocketException) {
                e.printStackTrace()
                Resource.Error(
                    message = "Something Went Wrong",
                    data = null

                )
            }
        }
    }
}
