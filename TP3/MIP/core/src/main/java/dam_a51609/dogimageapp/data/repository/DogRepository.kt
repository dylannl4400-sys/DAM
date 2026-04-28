package dam_a51609.dogimageapp.data.repository

import dam_a51609.dogimageapp.data.api.DogApiService
import dam_a51609.dogimageapp.data.model.ImageItem

class DogRepository(private val apiService: DogApiService) {

    private val _cache = mutableListOf<ImageItem>()
    val cache: List<ImageItem> get() = _cache

    private val _favorites = mutableListOf<ImageItem>()
    val favorites: List<ImageItem> get() = _favorites.toList()

    suspend fun getRandomDogImage(): ImageItem? {
        val response = apiService.getRandomDogImage()
        if (response.isSuccessful) {
            response.body()?.let { newItem ->
                val breed = extractBreedFromUrl(newItem.url)
                val isFav = _favorites.any { it.url == newItem.url }
                val itemWithMetadata = newItem.copy(breed = breed, isFavorite = isFav)
                
                if (_cache.size >= 50) {
                    _cache.removeAt(0)
                }
                _cache.add(itemWithMetadata)
                
                return itemWithMetadata
            }
        }
        return null
    }

    fun toggleFavorite(item: ImageItem): List<ImageItem> {
        val existingIndex = _favorites.indexOfFirst { it.url == item.url }

        if (existingIndex != -1) {
            _favorites.removeAt(existingIndex)
        } else {
            if (_favorites.size >= 5) {
                _favorites.removeAt(0)
            }
            _favorites.add(item.copy(isFavorite = true))
        }
        return favorites
    }

    private fun extractBreedFromUrl(url: String): String {
        return try {
            // URL format: https://images.dog.ceo/breeds/breed-name/image.jpg
            val parts = url.split("/")
            val breedsIndex = parts.indexOf("breeds")
            if (breedsIndex != -1 && breedsIndex + 1 < parts.size) {
                val rawBreed = parts[breedsIndex + 1]
                formatBreedName(rawBreed)
            } else {
                "Unknown Breed"
            }
        } catch (e: Exception) {
            "Unknown Breed"
        }
    }

    private fun formatBreedName(rawBreed: String): String {
        return rawBreed.split("-")
            .joinToString(" ") { word ->
                word.replaceFirstChar { it.uppercase() }
            }
    }
}
