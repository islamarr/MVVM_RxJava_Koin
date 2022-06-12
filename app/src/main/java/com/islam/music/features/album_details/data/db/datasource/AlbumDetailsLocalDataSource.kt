package com.islam.music.features.album_details.data.db.datasource

import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.top_albums.domain.entites.Album

interface AlbumDetailsLocalDataSource {
    suspend fun addToFavoriteList(album: AlbumEntity)
    suspend fun removeFromFavoriteList(album: AlbumEntity)
    fun getFavoriteList(): List<Album>
    fun getOneFavoriteAlbum(albumParams: AlbumParams): AlbumEntity
}