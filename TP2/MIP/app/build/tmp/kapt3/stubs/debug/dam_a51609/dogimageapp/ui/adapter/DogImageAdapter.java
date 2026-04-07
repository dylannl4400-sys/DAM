package dam_a51609.dogimageapp.ui.adapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00112\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0011\u0012B-\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\t\u001a\u00020\u00062\n\u0010\n\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001c\u0010\r\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fH\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Ldam_a51609/dogimageapp/ui/adapter/DogImageAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Ldam_a51609/dogimageapp/data/model/ImageItem;", "Ldam_a51609/dogimageapp/ui/adapter/DogImageAdapter$DogViewHolder;", "onItemClick", "Lkotlin/Function1;", "", "onFavoriteClick", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DiffCallback", "DogViewHolder", "app_debug"})
public final class DogImageAdapter extends androidx.recyclerview.widget.ListAdapter<dam_a51609.dogimageapp.data.model.ImageItem, dam_a51609.dogimageapp.ui.adapter.DogImageAdapter.DogViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<dam_a51609.dogimageapp.data.model.ImageItem, kotlin.Unit> onItemClick = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<dam_a51609.dogimageapp.data.model.ImageItem, kotlin.Unit> onFavoriteClick = null;
    @org.jetbrains.annotations.NotNull()
    public static final dam_a51609.dogimageapp.ui.adapter.DogImageAdapter.DiffCallback DiffCallback = null;
    
    public DogImageAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super dam_a51609.dogimageapp.data.model.ImageItem, kotlin.Unit> onItemClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super dam_a51609.dogimageapp.data.model.ImageItem, kotlin.Unit> onFavoriteClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public dam_a51609.dogimageapp.ui.adapter.DogImageAdapter.DogViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    dam_a51609.dogimageapp.ui.adapter.DogImageAdapter.DogViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Ldam_a51609/dogimageapp/ui/adapter/DogImageAdapter$DiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Ldam_a51609/dogimageapp/data/model/ImageItem;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_debug"})
    public static final class DiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<dam_a51609.dogimageapp.data.model.ImageItem> {
        
        private DiffCallback() {
            super();
        }
        
        @java.lang.Override()
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull()
        dam_a51609.dogimageapp.data.model.ImageItem oldItem, @org.jetbrains.annotations.NotNull()
        dam_a51609.dogimageapp.data.model.ImageItem newItem) {
            return false;
        }
        
        @java.lang.Override()
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull()
        dam_a51609.dogimageapp.data.model.ImageItem oldItem, @org.jetbrains.annotations.NotNull()
        dam_a51609.dogimageapp.data.model.ImageItem newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Ldam_a51609/dogimageapp/ui/adapter/DogImageAdapter$DogViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Ldam_a51609/dogimageapp/databinding/ItemDogImageBinding;", "(Ldam_a51609/dogimageapp/ui/adapter/DogImageAdapter;Ldam_a51609/dogimageapp/databinding/ItemDogImageBinding;)V", "bind", "", "item", "Ldam_a51609/dogimageapp/data/model/ImageItem;", "app_debug"})
    public final class DogViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final dam_a51609.dogimageapp.databinding.ItemDogImageBinding binding = null;
        
        public DogViewHolder(@org.jetbrains.annotations.NotNull()
        dam_a51609.dogimageapp.databinding.ItemDogImageBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        dam_a51609.dogimageapp.data.model.ImageItem item) {
        }
    }
}