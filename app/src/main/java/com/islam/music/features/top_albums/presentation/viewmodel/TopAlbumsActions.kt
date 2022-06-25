package com.islam.music.features.top_albums.presentation.viewmodel

import com.islam.music.common.Action

sealed class TopAlbumsActions : Action{
    data class LoadAllAlbums(val artistName: String) : TopAlbumsActions()
}
