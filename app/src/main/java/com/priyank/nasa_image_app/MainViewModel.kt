package com.priyank.nasa_image_app

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyank.nasa_image_app.domain.repository.ImageRepository
import com.priyank.nasa_image_app.util.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
    private val networkObserver: ConnectivityObserver
) : ViewModel() {

    init {
        networkObserver.observe().onEach { Log.d("Network Status", it.toString()) }.launchIn(viewModelScope)
    }
}
