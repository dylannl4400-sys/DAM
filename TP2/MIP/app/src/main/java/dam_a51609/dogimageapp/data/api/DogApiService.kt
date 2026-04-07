package dam_a51609.dogimageapp.data.api

import dam_a51609.dogimageapp.data.model.ImageItem
import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): Response<ImageItem>

    companion object {
        const val BASE_URL = "https://dog.ceo/api/"
    }
}
