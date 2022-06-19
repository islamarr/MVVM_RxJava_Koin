package com.islam.music.features.album_details.domain.usecases

import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SetFavoriteUseCase @Inject constructor(private val repository: AlbumDetailsRepository) {

    fun execute(isAdd: Boolean, albumEntity: AlbumEntity) {
        if (isAdd) repository.addToFavoriteList(albumEntity)
        else repository.removeFromFavoriteList(albumEntity)
    }

}