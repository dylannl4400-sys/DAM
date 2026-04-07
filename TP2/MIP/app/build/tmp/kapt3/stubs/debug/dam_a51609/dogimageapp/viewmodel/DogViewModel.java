package dam_a51609.dogimageapp.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0017\u001a\u00020\u0018J\u0010\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\nH\u0002J\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\bR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0011R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\r0\u000f8F\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Ldam_a51609/dogimageapp/viewmodel/DogViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Ldam_a51609/dogimageapp/data/repository/DogRepository;", "(Ldam_a51609/dogimageapp/data/repository/DogRepository;)V", "_dogImages", "Landroidx/lifecycle/MutableLiveData;", "", "Ldam_a51609/dogimageapp/data/model/ImageItem;", "_error", "", "_favorites", "_isLoading", "", "dogImages", "Landroidx/lifecycle/LiveData;", "getDogImages", "()Landroidx/lifecycle/LiveData;", "error", "getError", "favorites", "getFavorites", "isLoading", "fetchRandomDogImage", "", "handleOfflineScenario", "errorMessage", "toggleFavorite", "item", "app_debug"})
public final class DogViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final dam_a51609.dogimageapp.data.repository.DogRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<dam_a51609.dogimageapp.data.model.ImageItem>> _dogImages = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<dam_a51609.dogimageapp.data.model.ImageItem>> _favorites = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> error = null;
    
    public DogViewModel(@org.jetbrains.annotations.NotNull()
    dam_a51609.dogimageapp.data.repository.DogRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<dam_a51609.dogimageapp.data.model.ImageItem>> getDogImages() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<dam_a51609.dogimageapp.data.model.ImageItem>> getFavorites() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getError() {
        return null;
    }
    
    public final void fetchRandomDogImage() {
    }
    
    private final void handleOfflineScenario(java.lang.String errorMessage) {
    }
    
    public final void toggleFavorite(@org.jetbrains.annotations.NotNull()
    dam_a51609.dogimageapp.data.model.ImageItem item) {
    }
}