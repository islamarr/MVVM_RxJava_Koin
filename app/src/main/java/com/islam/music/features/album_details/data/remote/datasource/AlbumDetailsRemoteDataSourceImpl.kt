package com.islam.music.features.album_details.data.remote.datasource

import com.islam.music.features.album_details.data.db.AlbumDetailsToAlbumMapper
import com.islam.music.features.album_details.data.remote.api.AlbumDetailsAPIService
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import io.reactivex.rxjava3.core.Single


class AlbumDetailsRemoteDataSourceImpl (
    private val apiService: AlbumDetailsAPIService,
    private val albumDetailsToAlbumMapper: AlbumDetailsToAlbumMapper
) :
    AlbumDetailsRemoteDataSource {

    override fun getAlbumDetails(albumParams: AlbumParams): Single<AlbumEntity> {
        val response = apiService.getAlbumDetails(albumParams.artistName, albumParams.albumName)
        return response.map { albumDetailsToAlbumMapper.invoke(it.album) }
    }

}