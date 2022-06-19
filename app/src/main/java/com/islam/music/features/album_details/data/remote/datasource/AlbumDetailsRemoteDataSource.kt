package com.islam.music.features.album_details.data.remote.datasource

import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import io.reactivex.rxjava3.core.Single

interface AlbumDetailsRemoteDataSource {
    fun getAlbumDetails(albumParams: AlbumParams): Single<AlbumEntity>
}