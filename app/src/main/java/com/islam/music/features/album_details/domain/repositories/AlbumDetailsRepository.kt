package com.islam.music.features.album_details.domain.repositories

import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.album_details.domain.entites.SavedState
import com.islam.music.features.top_albums.domain.entites.Album
import io.reactivex.rxjava3.core.Single

interface AlbumDetailsRepository {
    fun getAlbumDetails(albumParams: AlbumParams): Single<AlbumEntity>
    fun addToFavoriteList(album: AlbumEntity)
    fun removeFromFavoriteList(album: AlbumEntity)
    fun getFavoriteList(): Single<List<Album>>
    fun getOneFavoriteAlbum(albumParams: AlbumParams): Single<AlbumEntity>
}