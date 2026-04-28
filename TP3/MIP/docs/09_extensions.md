# Extensions

---

## Feature 1: Loading Indicator

Description:
Display a loading indicator while fetching data from the API.

Tasks:
- Add loading state in ViewModel
- Show ProgressBar during API call
- Hide ProgressBar after data is loaded

UI Changes:
- ProgressBar visibility controlled dynamically

Implementation Plan:
1. Add LiveData<Boolean> isLoading in ViewModel
2. Update value before and after API call
3. Observe isLoading in MainActivity
4. Show/hide ProgressBar

---

## Feature 2: Image Details Screen

Description:
Allow users to view a larger version of the image with additional information.

Tasks:
- Create new Activity (DetailsActivity)
- Pass image URL via Intent
- Display image using Glide

UI Changes:
- New screen with ImageView and TextView

Implementation Plan:
1. Create DetailsActivity
2. Create activity_details.xml
3. Add Intent navigation from RecyclerView item click
4. Load image using Glide

---

## Feature 3: Favorites (FIFO max 5)

Description:
Users can mark images as favorites. Only the 5 most recent favorites are kept.

Tasks:
- Add favorite button to each item
- Store favorites in a FIFO queue
- Limit size to 5 items

UI Changes:
- Favorite icon (e.g., star) on each image
- Section or quick access to favorites

Implementation Plan:
1. Create data structure (Queue/List)
2. Add/remove favorites logic
3. Update UI when favorite is clicked
4. Ensure max size = 5

---

## Feature 4: Cache (max 50 items)

Description:
Store previously loaded images to improve performance and navigation.

Tasks:
- Maintain list of cached images
- Limit to 50 items
- Keep 10 before and 10 after current

UI Changes:
- No major UI changes (internal logic)

Implementation Plan:
1. Create cache list in Repository
2. Add new images to cache
3. Remove oldest when exceeding 50
4. Serve data from cache when possible

---

## Feature 5: Offline Support

Description:
Allow users to view cached images when offline.

Tasks:
- Detect network availability
- Load data from cache if offline

UI Changes:
- Optional message: "Offline mode"

Implementation Plan:
1. Check connectivity
2. If offline → use cache
3. Display message to user

---

## Feature 6: API Error Handling

Description:
Handle API failures gracefully.

Tasks:
- Catch exceptions
- Display error message

UI Changes:
- Toast or TextView with error message

Implementation Plan:
1. Add try/catch in Repository
2. Return error state to ViewModel
3. Observe error in Activity
4. Show message