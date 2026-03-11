package com.mememaker.app.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mememaker.app.databinding.ItemMemeBinding
import com.mememaker.app.model.Meme

class MemeAdapter(private val onMemeClick: (Meme) -> Unit) : ListAdapter<Meme, MemeAdapter.MemeViewHolder>(MemeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemeViewHolder {
        val binding = ItemMemeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MemeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MemeViewHolder(private val binding: ItemMemeBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onMemeClick(getItem(adapterPosition))
                }
            }
        }

        fun bind(meme: Meme) {
            binding.textViewName.text = meme.name
            Glide.with(binding.root.context)
                .load(meme.url)
                .centerCrop()
                .into(binding.imageViewMeme)
        }
    }

    class MemeDiffCallback : DiffUtil.ItemCallback<Meme>() {
        override fun areItemsTheSame(oldItem: Meme, newItem: Meme): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Meme, newItem: Meme): Boolean {
            return oldItem == newItem
        }
    }
}
