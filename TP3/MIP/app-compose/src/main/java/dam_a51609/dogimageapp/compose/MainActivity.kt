package dam_a51609.dogimageapp.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dam_a51609.dogimageapp.compose.ui.screens.DogDetailScreen
import dam_a51609.dogimageapp.compose.ui.screens.DogListScreen
import dam_a51609.dogimageapp.compose.viewmodel.ComposeDogViewModel
import dam_a51609.dogimageapp.compose.viewmodel.ComposeDogViewModelFactory
import dam_a51609.dogimageapp.data.api.DogApiService
import dam_a51609.dogimageapp.data.repository.DogRepository
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {

    private val apiService by lazy { DogApiService.create() }
    private val repository by lazy { DogRepository(apiService) }
    private val viewModel: ComposeDogViewModel by viewModels { ComposeDogViewModelFactory(repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DogAppNavigation(viewModel)
                }
            }
        }
        
        // Initial fetch
        if (viewModel.uiState.value.dogImages.isEmpty()) {
            viewModel.fetchRandomDogImage()
        }
    }
}

@Composable
fun DogAppNavigation(viewModel: ComposeDogViewModel) {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            DogListScreen(
                viewModel = viewModel,
                onNavigateToDetail = { dog ->
                    val encodedUrl = URLEncoder.encode(dog.url, StandardCharsets.UTF_8.toString())
                    navController.navigate("detail/$encodedUrl/${dog.breed}")
                }
            )
        }
        composable(
            route = "detail/{url}/{breed}",
            arguments = listOf(
                navArgument("url") { type = NavType.StringType },
                navArgument("breed") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")
            val breed = backStackEntry.arguments?.getString("breed")
            DogDetailScreen(
                imageUrl = url,
                breed = breed,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
