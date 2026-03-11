package com.mememaker.app.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mememaker.app.data.repository.MemeRepository
import com.mememaker.app.model.Meme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class GalleryState {
    object Loading : GalleryState()
    data class Success(val memes: List<Meme>) : GalleryState()
    data class Error(val message: String) : GalleryState()
}

class GalleryViewModel : ViewModel() {
    private val repository = MemeRepository()

    private val _uiState = MutableStateFlow<GalleryState>(GalleryState.Loading)
    val uiState: StateFlow<GalleryState> = _uiState.asStateFlow()

    init {
        fetchMemes()
    }

    private fun fetchMemes() {
        viewModelScope.launch {
            _uiState.value = GalleryState.Loading
            repository.fetchMemes().fold(
                onSuccess = { memes ->
                    _uiState.value = GalleryState.Success(memes)
                },
                onFailure = { error ->
                    _uiState.value = GalleryState.Error(error.message ?: "Unknown Error")
                }
            )
        }
    }
}
