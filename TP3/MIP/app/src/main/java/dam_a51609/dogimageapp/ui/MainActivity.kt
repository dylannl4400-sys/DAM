package dam_a51609.dogimageapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dam_a51609.dogimageapp.data.api.DogApiService
import dam_a51609.dogimageapp.data.repository.DogRepository
import dam_a51609.dogimageapp.databinding.ActivityMainBinding
import dam_a51609.dogimageapp.ui.adapter.DogImageAdapter
import dam_a51609.dogimageapp.viewmodel.DogViewModel
import dam_a51609.dogimageapp.viewmodel.DogViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DogImageAdapter

    // Simple manual dependency injection
    private val apiService by lazy {
        Retrofit.Builder()
            .baseUrl(DogApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApiService::class.java)
    }

    private val repository by lazy { DogRepository(apiService) }
    private val viewModel: DogViewModel by viewModels { DogViewModelFactory(repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
        setupRecyclerView()
        setupObservers()
        setupListeners()

        // Initial fetch
        if (viewModel.dogImages.value.isNullOrEmpty()) {
            viewModel.fetchRandomDogImage()
        }
    }

    private fun setupAdapter() {
        adapter = DogImageAdapter(
            onItemClick = { item ->
                val intent = android.content.Intent(this, DetailsActivity::class.java).apply {
                    putExtra(DetailsActivity.EXTRA_IMAGE_URL, item.url)
                    putExtra(DetailsActivity.EXTRA_BREED, item.breed)
                }
                startActivity(intent)
            },
            onFavoriteClick = { item ->
                viewModel.toggleFavorite(item)
            }
        )
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.dogImages.observe(this) { images ->
            adapter.submitList(images)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupListeners() {
        binding.fabRefresh.setOnClickListener {
            viewModel.fetchRandomDogImage()
        }
    }
}
