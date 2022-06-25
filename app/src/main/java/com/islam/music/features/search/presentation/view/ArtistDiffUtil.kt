package com.islam.music.features.search.presentation.view

import com.islam.music.common.view.BaseDiffCallback
import com.islam.music.features.search.domain.entites.Artist

open class ArtistDiffUtil : BaseDiffCallback<Artist>() {
    override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
        return oldItem.id == newItem.id
    }

}