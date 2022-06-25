package com.islam.music.features.main_screen.presentation.view

import com.islam.music.common.view.BaseDiffCallback
import com.islam.music.features.top_albums.domain.entites.Album

open class AlbumsDiffUtil : BaseDiffCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.id == newItem.id
    }
}