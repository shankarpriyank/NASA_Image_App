package com.priyank.nasa_image_app

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.priyank.nasa_image_app.domain.repository.ImageRepository
import com.priyank.nasa_image_app.navigation.Screen
import com.priyank.nasa_image_app.presentation.model.ImageInfoState
import com.priyank.nasa_image_app.util.ConnectivityObserver
import com.priyank.nasa_image_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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

    private val _state = mutableStateOf(ImageInfoState())
    val state: State<ImageInfoState> = _state

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
                    Log.e("Succcess", "Chaiye")

                    _state.value = state.value.copy(
                        images = result.data,
                        loading = false
                    )
                }

                is Resource.Loading -> {
                    if (!result.data.isNullOrEmpty()) {
                        _state.value = state.value.copy(
                            images = result.data,
                            loading = false
                        )
                    } else {
                        _state.value = state.value.copy(
                            images = result.data,
                            loading = true
                        )
                    }
                }

                is Resource.Error -> {
                    Log.d("Error With Data ", "NULL ${result.data.isNullOrEmpty()}")
                }
            }
        }.collect {
        }
    }
}
