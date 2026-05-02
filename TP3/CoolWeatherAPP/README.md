# Assignment 2 — Cool Weather APP
Course: LEIM
Student(s): Dylan Loyola A51609
Date: 12/04/2026

## 1. Introduction
The **Cool Weather APP** is an Android application designed to provide real-time meteorological data for a specific location. The app features a customizable and responsive interface that adapts to different screen sizes and orientations. By default, it displays weather information for Lisbon, but users can input any latitude and longitude coordinates to retrieve localized data including temperature, pressure, wind speed, and wind direction.

## 2. System Overview
The application integrates with the **Open-Meteo API** to fetch high-precision weather forecasts. Key features include:
- **Real-time Data Fetching**: Retrieves current weather variables via JSON HTTP requests.
- **Responsive Design**: Custom layouts for portrait and landscape modes, as well as tablet support.
- **Dynamic Theming**: Backgrounds and icons change based on the time of day (Day/Night) and weather conditions.
- **Localization**: Full support for Portuguese and English languages.
- **GPS Integration**: Automatically detects device coordinates to show local weather on startup.

## 3. Architecture and Design
The project follows the **Model-View-ViewModel (MVVM)** design pattern to ensure a clean separation of concerns and maintainable code.

- **Model**: Represented by `WeatherData.kt`, containing data classes mapped to the API response structure.
- **ViewModel**: `WeatherViewModel.kt` handles the business logic, API calls via background threads, and data processing. It exposes properties through `LiveData` for reactive UI updates.
- **View**: `MainActivity.kt` observes `LiveData` and manages UI-specific logic like permissions and screen orientation changes.
- **Data Binding**: Utilized to bind UI components directly to the ViewModel's state, reducing boilerplate code in the Activity.

## 4. Implementation

### Data Modeling (`WeatherData.kt`)
The API response is parsed using **Gson**. The data classes reflect the hierarchical structure of the Open-Meteo JSON, facilitating easy access to `current_weather` and `hourly` blocks.

### Logic and State (`WeatherViewModel.kt`)
The ViewModel manages the application state. It uses a `thread` block to perform network requests, preventing UI freezing. 
- **Weather Code Mapping**: Instead of hardcoding enums, the app retrieves descriptions and icon names from `res/values/weather_codes.xml` using `resources.getIdentifier`.
- **Thread-safe Updates**: Used `postValue()` on `MutableLiveData` to safely update the UI from the background worker thread.

### User Interface (`activity_main.xml`)
The UI consists of:
- `EditText` for latitude and longitude manual input.
- `ImageView` for visual weather condition representation.
- `TextView` elements for meteorological details.
- Background containers that swap resources dynamically.

### Localization
Implemented using `strings.xml` for both Portuguese and English. The system automatically switches based on the device's locale settings.

## 5. Testing and Validation
Testing was conducted using different Android Virtual Devices (AVDs):
- **Pixel 3**: Verified portrait layout and background alignment.
- **Landscape Mode**: Ensured background images were correctly cropped and elements re-flowed without overlapping.
- **Coordinates Testing**: Tested various global coordinates to ensure API robustness (e.g., Tokyo, New York) and verified weather icon accuracy.
- **Permission Flow**: Validated that the app falls back to Lisbon (38.076, -9.12) if location permissions are denied.

## 6. Usage Instructions
1. Launch the application.
2. Allow location permissions to see local weather automatically.
3. To check another location, enter the Latitude and Longitude in the numeric fields.
4. Press the "Update" button to fetch new data.
5. Rotate the device to see the adapted landscape layout.

---

# Development Process

## 12. Version Control and Commit History
The project was developed incrementally, following the lab milestones:
- UI design and static resources.
- API integration and JSON parsing.
- Refactoring to MVVM and Data Binding.
- Adding GPS and Localization support.

## 13. Difficulties and Lessons Learned
- **Architecture Shift**: Moving from a traditional "all-in-Activity" approach to MVVM was challenging but simplified state management significantly.
- **Threading**: Understanding that `runOnUiThread` is necessary for UI updates from background threads (unless using `postValue` which handles this internally).
- **Resource Management**: Learning to use `getIdentifier` to dynamically fetch drawables based on string keys provided by the XML resource arrays.
- **Documentation**: Extensive use of the [Android Developers Guide](https://developer.android.com/guide) was crucial for implementing `ViewBinding` and `LocationManager`.

## 14. Future Improvements
- **Dynamic Day/Night Detection**: Calculate if it's currently day or night at the target location using the API's sunrise/sunset data instead of a static boolean.
- **Graphing**: Implement a chart to visualize the hourly temperature trends.
- **Search by City**: Integrate a Geocoding API to allow searches by city name instead of just coordinates.

## 15. AI Usage Disclosure
This project utilized **Antigravity**, an AI coding assistant, for structured documentation generation (README.md), code architecture suggestions (MVVM pattern), and debugging help during the refactoring phase. AI tools were used to ensure professional formatting and clarity in the technical explanations.
