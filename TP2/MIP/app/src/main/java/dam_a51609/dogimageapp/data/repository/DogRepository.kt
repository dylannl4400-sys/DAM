package dam_a51609.dogimageapp.data.repository

import dam_a51609.dogimageapp.data.api.DogApiService
import dam_a51609.dogimageapp.data.model.ImageItem
import retrofit2.Response

class DogRepository(private val apiService: DogApiService) {
    suspend fun getRandomDogImage(): Response<ImageItem> {
        return apiService.getRandomDogImage()
    }
}
