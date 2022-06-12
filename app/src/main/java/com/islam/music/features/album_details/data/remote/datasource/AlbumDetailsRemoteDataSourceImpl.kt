package com.islam.music.features.album_details.data.remote.datasource

import com.islam.music.features.album_details.data.db.AlbumDetailsToAlbumMapper
import com.islam.music.features.album_details.data.remote.api.AlbumDetailsAPIService
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import javax.inject.Inject

class AlbumDetailsRemoteDataSourceImpl @Inject constructor(
    private val apiService: AlbumDetailsAPIService,
    private val albumDetailsToAlbumMapper: AlbumDetailsToAlbumMapper
) :
    AlbumDetailsRemoteDataSource {

    override suspend fun getAlbumDetails(albumParams: AlbumParams): AlbumEntity {
        val response = apiService.getAlbumDetails(albumParams.artistName, albumParams.albumName)
        return albumDetailsToAlbumMapper.invoke(response.album)
    }
}