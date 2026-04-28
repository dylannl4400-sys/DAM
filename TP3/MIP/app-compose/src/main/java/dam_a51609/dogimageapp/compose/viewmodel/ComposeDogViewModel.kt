package dam_a51609.dogimageapp.compose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dam_a51609.dogimageapp.data.model.ImageItem
import dam_a51609.dogimageapp.data.repository.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DogUiState(
    val dogImages: List<ImageItem> = emptyList(),
    val favorites: List<ImageItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ComposeDogViewModel(private val repository: DogRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(DogUiState())
    val uiState: StateFlow<DogUiState> = _uiState.asStateFlow()

    init {
        // Initialize favorites from repository
        _uiState.update { it.copy(favorites = repository.favorites) }
    }

    fun fetchRandomDogImage() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val newItem = repository.getRandomDogImage()
                if (newItem != null) {
                    _uiState.update { state ->
                        state.copy(
                            dogImages = listOf(newItem) + state.dogImages,
                            isLoading = false
                        )
                    }
                } else {
                    handleError("Error fetching image")
                }
            } catch (e: Exception) {
                handleError(e.localizedMessage ?: "Unknown error")
            } finally {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun handleError(message: String) {
        val cached = repository.cache
        if (cached.isNotEmpty()) {
            _uiState.update { state ->
                val newImages = (cached + state.dogImages).distinctBy { it.url }
                state.copy(
                    dogImages = newImages,
                    error = "Offline / Error. Loaded from cache."
                )
            }
        } else {
            _uiState.update { it.copy(error = message) }
        }
    }

    fun toggleFavorite(item: ImageItem) {
        val updatedFavorites = repository.toggleFavorite(item)
        _uiState.update { state ->
            val updatedImages = state.dogImages.map { dog ->
                val isFav = updatedFavorites.any { it.url == dog.url }
                dog.copy(isFavorite = isFav)
            }
            state.copy(
                favorites = updatedFavorites,
                dogImages = updatedImages
            )
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

class ComposeDogViewModelFactory(private val repository: DogRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ComposeDogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ComposeDogViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
