package dam_a51609.dogimageapp.data.model

import com.google.gson.annotations.SerializedName

data class ImageItem(
    @SerializedName("message") val url: String,
    @SerializedName("status") val status: String,
    val isFavorite: Boolean = false,
    val breed: String = "Unknown Breed"
)
