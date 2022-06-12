package com.islam.music.features.search.presentation.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.islam.music.R
import com.islam.music.common.IMAGE_SIZE_MULTIPLIER
import com.islam.music.common.view.BaseListAdapter
import com.islam.music.databinding.OneGeneralItemBinding
import com.islam.music.features.search.domain.entites.Artist

class ArtistsAdapter(private val onSearchItemClickListener: OnSearchItemClickListener) :
    BaseListAdapter<Artist>(ArtistDiffUtil()) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return OneGeneralItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bind(viewBinding: ViewBinding, item: Artist, itemView: View) {
        val binding = viewBinding as OneGeneralItemBinding
        binding.title.text = item.name
        loadImage(itemView.context, item.images?.get(0)?.url, binding)

        itemView.setOnClickListener {
            onSearchItemClickListener.onClick(artistName = item.name)
        }
    }

    private fun loadImage(context: Context, url: String?, binding: OneGeneralItemBinding) {
        Glide.with(context).load(url)
            .placeholder(R.drawable.ic_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(IMAGE_SIZE_MULTIPLIER)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.itemImage)
    }

}