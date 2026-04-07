# Prompts Log

## Prompt 1
Goal:
Summarize documentation

Prompt:
Read all documentation and summarize architecture and plan

Result:
AI correctly identified MVVM architecture, data model, and implementation steps.

## Prompt 2
Goal:
Update implementation plan and initialize project structure

Prompt:
Use Glide for image loading. Use package name dam_a51609.dogimageapp. Do not implement offline mode. Continue.

Result:
AI updated the implementation plan, created the task list, and initialized the root and app-level Gradle files and Manifest.

## Prompt 3
Goal:
Implement Data Model, API Service, and Repository

Prompt:
Continue

Result:
AI implemented `ImageItem.kt`, `DogApiService.kt`, and `DogRepository.kt`. The project structure is now taking shape with standard MVVM components.

## Prompt 4
Goal:
Implement ViewModel and UI Layouts

Prompt:
Continue

Result:
AI implemented `DogViewModel.kt`, `activity_main.xml`, and `item_dog_image.xml`. The app now has its state management and visual structure defined.

## Prompt 5
Goal:
Implement Adapter and MainActivity

Prompt:
Continue

Result:
AI implemented `DogImageAdapter.kt` and `MainActivity.kt`. The app is now fully functional with its core UI components connected.

## Prompt 6
Goal:
Finalize implementation and generate UI mockup

Prompt:
Continue

Result:
AI verified the implementation, fixed a dependency error in `MainActivity.kt`, marked all tasks as completed, and generated a high-fidelity mockup of the final app UI.

## Prompt 7
Goal:
Build, Deploy, and Run verification (Section 3.9)

Prompt:
3.9 Build, Deploy, and Run Using AntiGravity... Focus on manual verification according to the assignment.

Result:
AI resolved environment conflicts (Java 25 vs AGP 8.2), fixed resource errors, and successfully built, deployed, and executed the app using the integrated AntiGravity workflow. Verification via logcat confirmed successful API retrieval and launch.

## Prompt 8
Goal:
Plan and implement app extensions

Prompt:
Add the extensions defined in docs/09_extensions.md... Use in-memory storage... main list toggle UI.

Result:
AI created a comprehensive implementation plan for the 6 extensions (Details Screen, Favorites, Caching, Offline Support). User confirmed in-memory storage and main-list favorite icons. AI updated task list and is proceeding with implementation.

## Prompt 9
Goal:
Implementation of Extensions (Features 1-6)

Prompt:
Continue (following confirmation of in-memory/main-list toggles)

Result:
AI implemented Details Screen (Activity + Navigation), FIFO Favorites (max 5) with star icons, In-memory Cache (max 50) in Repository, and Offline Support with cache fallback. All features were verified via a final integrated build (BUILD SUCCESSFUL).

## Prompt 11
Goal:
MIP-2 3.3.1 - Metadata Extraction (Dog Breed)

Prompt:
Proceed with implementation of the MIP-2 requirement for image metadata (3.3.1)... Extend ImageItem... Format breed names... Display in Main and Details screens.

Result:
AI extended the data model with a non-null reed field, implemented URL-based extraction and formatting in the repository (e.g., "hound-plott" -> "Hound Plott"), and updated both MainActivity (RecyclerView) and DetailsActivity layouts and binding to display the metadata. Verified via an integrated build and successful deployment.
