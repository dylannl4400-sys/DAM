package dam_a51609.dogimageapp.compose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dam_a51609.dogimageapp.compose.ui.components.DogCard
import dam_a51609.dogimageapp.compose.viewmodel.ComposeDogViewModel
import dam_a51609.dogimageapp.data.model.ImageItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogListScreen(
    viewModel: ComposeDogViewModel,
    onNavigateToDetail: (ImageItem) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Dog Image App (Compose)") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.fetchRandomDogImage() }) {
                Icon(Icons.Default.Refresh, contentDescription = "Refresh")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            if (uiState.dogImages.isEmpty() && !uiState.isLoading) {
                Text(
                    text = "No images fetched yet.",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(uiState.dogImages) { dog ->
                        DogCard(
                            item = dog,
                            onItemClick = { onNavigateToDetail(it) },
                            onFavoriteClick = { viewModel.toggleFavorite(it) }
                        )
                    }
                }
            }

            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            uiState.error?.let { error ->
                Snackbar(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    action = {
                        TextButton(onClick = { viewModel.clearError() }) {
                            Text("OK")
                        }
                    }
                ) {
                    Text(error)
                }
            }
        }
    }
}
