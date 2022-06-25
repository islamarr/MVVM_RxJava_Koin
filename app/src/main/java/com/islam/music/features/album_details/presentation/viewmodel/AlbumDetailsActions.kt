package com.islam.music.features.album_details.presentation.viewmodel

import com.islam.music.common.Action
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams

sealed class AlbumDetailsActions : Action {
    data class AlbumDetailsAction(val albumParams: AlbumParams) :
        AlbumDetailsActions()

    data class SetFavoriteAction(val isAdd: Boolean, val albumEntity: AlbumEntity) :
        AlbumDetailsActions()
}
