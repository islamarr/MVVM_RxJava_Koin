package com.islam.music.features.top_albums.presentation.viewmodel

import com.islam.music.common.ViewState
import com.islam.music.features.top_albums.domain.entites.Album


sealed class TopAlbumsStates : ViewState {
    object InitialState : TopAlbumsStates()
    object Loading : TopAlbumsStates()
    data class ShowErrorMessage(val reason: String? = null) :
        TopAlbumsStates()

    data class TopAlbumsListLoaded(val topAlbumsList: List<Album>) : TopAlbumsStates()
    object EmptyTopAlbumsList : TopAlbumsStates()
}
