## Modular Architecture (MIP-3)

The project is divided into three modules:

- :core  
  Contains:
  - Data models (ImageItem)
  - API service (DogApiService)
  - Repository (DogRepository)
  - Business logic (breed extraction, cache, favorites)

- :app-xml  
  - XML-based UI (MainActivity, RecyclerView, DetailsActivity)
  - Consumes :core

- :app-compose  
  - Jetpack Compose UI
  - Uses composables and state
  - Consumes :core