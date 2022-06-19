package com.islam.music.features.album_details.domain.usecases

import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository

class SetFavoriteUseCase(private val repository: AlbumDetailsRepository) {

    fun execute(isAdd: Boolean, albumEntity: AlbumEntity) {
        if (isAdd) repository.addToFavoriteList(albumEntity)
        else repository.removeFromFavoriteList(albumEntity)
    }

}