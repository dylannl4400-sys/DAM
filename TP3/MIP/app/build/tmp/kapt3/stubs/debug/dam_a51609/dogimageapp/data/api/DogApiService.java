package dam_a51609.dogimageapp.data.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005\u00a8\u0006\u0007"}, d2 = {"Ldam_a51609/dogimageapp/data/api/DogApiService;", "", "getRandomDogImage", "Lretrofit2/Response;", "Ldam_a51609/dogimageapp/data/model/ImageItem;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public abstract interface DogApiService {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String BASE_URL = "https://dog.ceo/api/";
    @org.jetbrains.annotations.NotNull()
    public static final dam_a51609.dogimageapp.data.api.DogApiService.Companion Companion = null;
    
    @retrofit2.http.GET(value = "breeds/image/random")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRandomDogImage(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<dam_a51609.dogimageapp.data.model.ImageItem>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Ldam_a51609/dogimageapp/data/api/DogApiService$Companion;", "", "()V", "BASE_URL", "", "app_debug"})
    public static final class Companion {
        @org.jetbrains.annotations.NotNull()
        public static final java.lang.String BASE_URL = "https://dog.ceo/api/";
        
        private Companion() {
            super();
        }
    }
}