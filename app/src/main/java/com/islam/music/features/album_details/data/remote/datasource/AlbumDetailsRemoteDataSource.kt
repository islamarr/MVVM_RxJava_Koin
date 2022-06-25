package com.islam.music.features.album_details.data.remote.datasource

import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams

interface AlbumDetailsRemoteDataSource {
    suspend fun getAlbumDetails(albumParams: AlbumParams): AlbumEntity
}