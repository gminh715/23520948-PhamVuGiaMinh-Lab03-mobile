/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.naturegallery.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.naturegallery.NatureGalleryApplication
import com.example.naturegallery.data.NaturePhotosRepository
import com.example.naturegallery.model.NaturePhoto
import kotlinx.coroutines.launch
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface NatureUiState {
    data class Success(val photos: List<NaturePhoto>) : NatureUiState
    object Error : NatureUiState
    object Loading : NatureUiState
}

class NatureViewModel(private val naturePhotosRepository: NaturePhotosRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var natureUiState: NatureUiState by mutableStateOf(NatureUiState.Loading)
        private set

    /**
     * Call getNaturePhotos() on init so we can display status immediately.
     */
    init {
        getNaturePhotos()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [NaturePhoto] [List] [MutableList].
     */
    fun getNaturePhotos() {
        viewModelScope.launch {
            natureUiState = NatureUiState.Loading
            natureUiState = try {
                NatureUiState.Success(naturePhotosRepository.getNaturePhotos())
            } catch (_: IOException) {
                NatureUiState.Error
            } catch (_: HttpException) {
                NatureUiState.Error
            } catch (_: SerializationException) {
                NatureUiState.Error
            }
        }
    }

    /**
     * Factory for [NatureViewModel] that takes [NaturePhotosRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NatureGalleryApplication)
                val naturePhotosRepository = application.container.naturePhotosRepository
                NatureViewModel(naturePhotosRepository = naturePhotosRepository)
            }
        }
    }
}
