package dam_a51609.dogimageapp.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\rH\u0002J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012H\u0086@\u00a2\u0006\u0002\u0010\u0013R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t8F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0014"}, d2 = {"Ldam_a51609/dogimageapp/data/repository/DogRepository;", "", "apiService", "Ldam_a51609/dogimageapp/data/api/DogApiService;", "(Ldam_a51609/dogimageapp/data/api/DogApiService;)V", "_cache", "", "Ldam_a51609/dogimageapp/data/model/ImageItem;", "cache", "", "getCache", "()Ljava/util/List;", "extractBreedFromUrl", "", "url", "formatBreedName", "rawBreed", "getRandomDogImage", "Lretrofit2/Response;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class DogRepository {
    @org.jetbrains.annotations.NotNull()
    private final dam_a51609.dogimageapp.data.api.DogApiService apiService = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<dam_a51609.dogimageapp.data.model.ImageItem> _cache = null;
    
    public DogRepository(@org.jetbrains.annotations.NotNull()
    dam_a51609.dogimageapp.data.api.DogApiService apiService) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<dam_a51609.dogimageapp.data.model.ImageItem> getCache() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getRandomDogImage(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<dam_a51609.dogimageapp.data.model.ImageItem>> $completion) {
        return null;
    }
    
    private final java.lang.String extractBreedFromUrl(java.lang.String url) {
        return null;
    }
    
    private final java.lang.String formatBreedName(java.lang.String rawBreed) {
        return null;
    }
}