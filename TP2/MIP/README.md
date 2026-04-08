# Dog Image App - AntiGravity MIP-2

This is a native Android application developed as part of the Multi-phase Implementation Project (MIP-2). It follows a strictly clean **MVVM (Model-View-ViewModel)** architecture and utilizes modern Android components like **Retrofit**, **Glide**, and **ViewBinding**.

## 🎯 Application Purpose

The primary goal of the Dog Image App is to provide a seamless interface for exploring canine diversity. Key objectives include:
- **Image Discovery:** Dynamically fetching and displaying random images from the Dog API.
- **Metadata Extraction (3.3.1):** Automatically parsing and formatting dog breed information from REST URLs (e.g., `hound-afghan` → `Hound Afghan`).
- **State Management:** Maintaining a **Favorites list** (with a FIFO policy of 5 items) and an **In-memory Cache** (storing the last 50 images).
- **Graceful Performance:** Providing **Offline Support** by serving cached images when connectivity is limited.

## 🌐 API Used

The application integrates with the **Dog API** (Public REST API):
- **Base URL:** `https://dog.ceo/api/`
- **Endpoint:** `breeds/image/random`
- **Official Documentation:** [dog.ceo/dog-api](https://dog.ceo/dog-api)

## 📸 Screenshots

Please include the following screenshots in this section to demonstrate the core functionality:

1.  **Main Image Stream (main_screen.png):**
    ![Main Screen](screenshots/main.png)
    - Shows the main scrollable list of cards.
    - Captures: Breed names (metadata) below each image and the star icon for favoriting.
2.  **Detailed View (details_screen.png):**
    ![Detailed View](screenshots/details.png)
    - Shows the high-quality full-screen view accessed via card tap.
    - Captures: The breed name at the top of the screen and the navigation back button.

## 🚀 Running the Project

To build and run the Dog Image App within the **AntiGravity IDE**, follow these steps:

1.  **Open Project:** Open the `MIP` folder in AntiGravity.
2.  **JDK Setup:** Ensure the project is using **JDK 21** as configured in `gradle.properties`.
3.  **Build:** Click the **Integrated Build** action or use the `/build` workflow. This will trigger the Gradle task `:app:assembleDebug`.
4.  **Launch:** Select an Android Emulator (e.g., `emulator-5554`) and click **Run**.
5.  **Explore:** Use the **Floating Action Button (FAB)** to refresh images and the **Star icon** to manage your 5 recent favorites.

---
**Course:** LEIM
**Developer:** Dylan Loyola A51609 51D
**Platform:** AntiGravity Agentic IDE