package com.priyank.nasa_image_app

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyank.nasa_image_app.domain.repository.ImageRepository
import com.priyank.nasa_image_app.presentation.model.ImageInfoState
import com.priyank.nasa_image_app.util.ConnectivityObserver
import com.priyank.nasa_image_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
    private val networkObserver: ConnectivityObserver
) : ViewModel() {

    private val _state = mutableStateOf(ImageInfoState())
    val state: State<ImageInfoState> = _state

    init {
        networkObserver.observe().onEach { Log.d("Network Status", it.toString()) }
            .launchIn(viewModelScope)

        viewModelScope.launch {
            imageRepository.getImages().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            images = result.data!!,
                            loading = false
                        )
                    }

                    is Resource.Loading -> {
                        TODO()
                    }

                    is Resource.Error -> {
                        TODO()
                    }
                }
            }.collectLatest { }
        }
    }
}
