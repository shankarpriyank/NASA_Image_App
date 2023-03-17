package com.priyank.nasa_image_app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.priyank.nasa_image_app.data.model.ImageInfo
import com.priyank.nasa_image_app.domain.repository.ImageRepository
import com.priyank.nasa_image_app.navigation.Screen
import com.priyank.nasa_image_app.util.ConnectivityObserver
import com.priyank.nasa_image_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(DelicateCoroutinesApi::class)
@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
    private val networkObserver: ConnectivityObserver
) : ViewModel() {
    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _imageList = MutableStateFlow(emptyList<ImageInfo>())
    val imageList = _imageList.asStateFlow()

    private val _loading = MutableStateFlow(true)
    val isloading = _loading.asStateFlow()

    init {
        networkObserver.observe().onEach { Log.d("Network Status", it.toString()) }
            .launchIn(GlobalScope)
        GlobalScope.launch(Dispatchers.Main) {
            getImages()
        }
    }

    fun openImageDetailScreen(id: Int, navHostController: NavHostController) {
        navHostController.navigate(Screen.ImageDetail.withArgs(id))
    }

    suspend fun getImages() {

        Log.d("Get Images", "Function Called")

        imageRepository.getImages().onEach { result ->
            when (result) {

                is Resource.Success -> {
                    _imageList.value = result.data!!
                    _loading.value = false
                }

                is Resource.Loading -> {
                    if (!result.data.isNullOrEmpty()) {
                        _imageList.value = result.data!!
                        _loading.value = false
                    } else {
                        _loading.value = false
                    }
                }

                is Resource.Error -> {
                    _eventFlow.emit(UIEvent.ShowToast(result.message ?: "Something Went Wrong"))
                }
            }
        }.collect {
        }
    }
    sealed class UIEvent {
        data class ShowToast(val message: String) : UIEvent()
    }
}
