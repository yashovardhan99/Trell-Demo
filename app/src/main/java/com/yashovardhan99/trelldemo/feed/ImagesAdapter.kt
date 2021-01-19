package com.yashovardhan99.trelldemo.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.yashovardhan99.trelldemo.R
import com.yashovardhan99.trelldemo.databinding.CardImageBinding

class ImagesAdapter : PagingDataAdapter<Image, ImagesAdapter.ImageViewHolder>(ImageDiffUtils()) {

    class ImageViewHolder(private val binding: CardImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image?) {
            binding.image = image
            binding.imageView.load(image?.downloadUrl) {
                crossfade(true)
                placeholder(R.drawable.image_placeholder)
            }
        }

    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardImageBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }
}

class ImageDiffUtils : DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }

}