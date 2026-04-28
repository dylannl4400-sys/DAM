package dam_a51609.dogimageapp.data.repository

import dam_a51609.dogimageapp.data.api.DogApiService
import dam_a51609.dogimageapp.data.model.ImageItem
import retrofit2.Response

class DogRepository(private val apiService: DogApiService) {

    private val _cache = mutableListOf<ImageItem>()
    val cache: List<ImageItem> get() = _cache

    suspend fun getRandomDogImage(): Response<ImageItem> {
        val response = apiService.getRandomDogImage()
        if (response.isSuccessful) {
            response.body()?.let { newItem ->
                val breed = extractBreedFromUrl(newItem.url)
                val itemWithMetadata = newItem.copy(breed = breed)
                
                if (_cache.size >= 50) {
                    _cache.removeAt(0)
                }
                _cache.add(itemWithMetadata)
                
                // Return the item with metadata
                return Response.success(itemWithMetadata)
            }
        }
        return response
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
