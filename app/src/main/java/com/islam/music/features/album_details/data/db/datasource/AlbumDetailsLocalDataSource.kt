package com.islam.music.features.album_details.data.db.datasource

import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.top_albums.domain.entites.Album
import io.reactivex.rxjava3.core.Single

interface AlbumDetailsLocalDataSource {
    suspend fun addToFavoriteList(album: AlbumEntity)
    suspend fun removeFromFavoriteList(album: AlbumEntity)
    fun getFavoriteList(): Single<List<Album>>
    fun getOneFavoriteAlbum(albumParams: AlbumParams): AlbumEntity
}