package com.islam.music.features.album_details.domain.usecases

import com.islam.music.features.album_details.domain.entites.AlbumDetailsData
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import io.reactivex.rxjava3.core.Single

class AlbumDetailsUseCase(private val repository: AlbumDetailsRepository) {

    fun execute(albumParams: AlbumParams): Single<AlbumDetailsData> =
        repository.getAlbumDetails(albumParams).map { AlbumDetailsData(it) }

}