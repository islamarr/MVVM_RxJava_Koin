package com.islam.music.features.album_details.presentation.view

import com.islam.music.common.view.BaseDiffCallback
import com.islam.music.features.album_details.domain.entites.Track

open class TrackerDiffUtil : BaseDiffCallback<Track>() {

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.attr.rank == newItem.attr.rank
    }
}