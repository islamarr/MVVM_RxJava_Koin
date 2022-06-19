package com.islam.music.features.album_details.domain.usecases

import com.islam.music.features.album_details.domain.entites.AlbumDetailsData
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@ViewModelScoped
class AlbumDetailsUseCase @Inject constructor(private val repository: AlbumDetailsRepository) {

    fun execute(albumParams: AlbumParams): Single<AlbumDetailsData> =
        repository.getAlbumDetails(albumParams).map { AlbumDetailsData(it) }

}