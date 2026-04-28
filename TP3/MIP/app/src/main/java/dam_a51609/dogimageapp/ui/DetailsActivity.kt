package dam_a51609.dogimageapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import dam_a51609.dogimageapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUrl = intent.getStringExtra(EXTRA_IMAGE_URL)
        val breed = intent.getStringExtra(EXTRA_BREED)

        if (imageUrl != null) {
            Glide.with(this)
                .load(imageUrl)
                .into(binding.detailedImageView)
        }

        binding.tvBreedDetail.text = breed ?: "Unknown Breed"

        binding.fabBack.setOnClickListener {
            finish()
        }
    }

    companion object {
        const val EXTRA_IMAGE_URL = "extra_image_url"
        const val EXTRA_BREED = "extra_breed"
    }
}
