package com.islam.music.features.album_details.presentation.viewmodel

import com.islam.music.common.ViewState
import com.islam.music.features.album_details.domain.entites.AlbumEntity

sealed class AlbumDetailsStates : ViewState {
    object InitialState : AlbumDetailsStates()
    object Loading : AlbumDetailsStates()
    data class ShowErrorMessage(val reason: String? = null) :
        AlbumDetailsStates()

    data class AlbumDetailsData(val albumDetails: AlbumEntity) :
        AlbumDetailsStates()

    data class SavedState(val isSaved: Boolean) : AlbumDetailsStates()
}
