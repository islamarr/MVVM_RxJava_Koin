package com.islam.music.features.album_details.domain.repositories

import com.islam.music.common.data.DataResponse
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.top_albums.domain.entites.Album
import io.reactivex.rxjava3.core.Single

interface AlbumDetailsRepository {
    suspend fun getAlbumDetails(albumParams: AlbumParams): DataResponse<AlbumEntity>
    suspend fun addToFavoriteList(album: AlbumEntity)
    suspend fun removeFromFavoriteList(album: AlbumEntity)
    fun getFavoriteList():Single<List<Album>>
    suspend fun getOneFavoriteAlbum(albumParams: AlbumParams): DataResponse<AlbumEntity>
}