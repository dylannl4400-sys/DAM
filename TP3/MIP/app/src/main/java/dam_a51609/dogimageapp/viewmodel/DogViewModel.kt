package dam_a51609.dogimageapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dam_a51609.dogimageapp.data.model.ImageItem
import dam_a51609.dogimageapp.data.repository.DogRepository
import kotlinx.coroutines.launch

class DogViewModel(private val repository: DogRepository) : ViewModel() {

    private val _dogImages = MutableLiveData<List<ImageItem>>(emptyList())
    val dogImages: LiveData<List<ImageItem>> get() = _dogImages

    private val _favorites = MutableLiveData<List<ImageItem>>(emptyList())
    val favorites: LiveData<List<ImageItem>> get() = _favorites

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    fun fetchRandomDogImage() {
        viewModelScope.launch {
            Log.d("DogViewModel", "Fetching random dog image...")
            _isLoading.value = true
            _error.value = null
            try {
                val response = repository.getRandomDogImage()
                if (response.isSuccessful) {
                    val newItem = response.body()
                    Log.d("DogViewModel", "Fetch success: ${newItem?.url}")
                    if (newItem != null) {
                        val currentList = _dogImages.value.orEmpty().toMutableList()
                        currentList.add(0, newItem)
                        _dogImages.value = currentList
                    }
                } else {
                    Log.e("DogViewModel", "Fetch error: ${response.code()}")
                    handleOfflineScenario("Error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("DogViewModel", "Fetch exception: ${e.localizedMessage}")
                handleOfflineScenario("Exception: ${e.localizedMessage}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun handleOfflineScenario(errorMessage: String) {
        val cached = repository.cache
        if (cached.isNotEmpty()) {
            _error.value = "Offline / Error. Loading from cache..."
            val currentList = _dogImages.value.orEmpty().toMutableList()
            // Add unique ones from cache that aren't already displayed
            cached.forEach { item ->
                if (!currentList.contains(item)) {
                    currentList.add(0, item)
                }
            }
            _dogImages.value = currentList
        } else {
            _error.value = errorMessage
        }
    }

    fun toggleFavorite(item: ImageItem) {
        val currentFavorites = _favorites.value.orEmpty().toMutableList()
        val existingIndex = currentFavorites.indexOfFirst { it.url == item.url }

        if (existingIndex != -1) {
            currentFavorites.removeAt(existingIndex)
        } else {
            if (currentFavorites.size >= 5) {
                currentFavorites.removeAt(0)
            }
            currentFavorites.add(item.copy(isFavorite = true))
        }
        _favorites.value = currentFavorites

        // Also update the main list to reflect the new state
        val currentImages = _dogImages.value.orEmpty().map { dog ->
            val isFav = currentFavorites.any { it.url == dog.url }
            dog.copy(isFavorite = isFav)
        }
        _dogImages.value = currentImages
    }
}
