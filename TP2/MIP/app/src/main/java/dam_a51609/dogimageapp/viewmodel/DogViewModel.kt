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
    val dogImages: LiveData<List<ImageItem>> = _dogImages

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

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
                    _error.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                Log.e("DogViewModel", "Fetch exception: ${e.localizedMessage}")
                _error.value = "Exception: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
