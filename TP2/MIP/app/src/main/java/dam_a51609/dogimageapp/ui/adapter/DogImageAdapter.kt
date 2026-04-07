package dam_a51609.dogimageapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dam_a51609.dogimageapp.data.model.ImageItem
import dam_a51609.dogimageapp.databinding.ItemDogImageBinding

class DogImageAdapter(
    private val onItemClick: (ImageItem) -> Unit,
    private val onFavoriteClick: (ImageItem) -> Unit
) : ListAdapter<ImageItem, DogImageAdapter.DogViewHolder>(DiffCallback) {

    inner class DogViewHolder(private val binding: ItemDogImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ImageItem) {
            Glide.with(binding.root.context)
                .load(item.url)
                .centerCrop()
                .placeholder(android.R.drawable.progress_horizontal)
                .error(android.R.drawable.stat_notify_error)
                .into(binding.ivDogImage)

            binding.btnFavorite.setImageResource(
                if (item.isFavorite) android.R.drawable.btn_star_big_on
                else android.R.drawable.btn_star_big_off
            )

            binding.tvBreed.text = item.breed

            binding.root.setOnClickListener { onItemClick(item) }
            binding.btnFavorite.setOnClickListener { onFavoriteClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val binding = ItemDogImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ImageItem>() {
        override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
            return oldItem == newItem
        }
    }
}
