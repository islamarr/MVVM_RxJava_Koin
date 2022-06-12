package com.islam.music.features.album_details.data.db.datasource

import com.islam.music.features.album_details.data.db.AlbumDao
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.main_screen.data.AlbumEntityToAlbumMapper
import com.islam.music.features.top_albums.domain.entites.Album
import javax.inject.Inject

class AlbumDetailsLocalDataSourceImpl @Inject constructor(
    private val albumDao: AlbumDao,
    private val albumEntityToAlbumMapper: AlbumEntityToAlbumMapper
) :
    AlbumDetailsLocalDataSource {
    override suspend fun addToFavoriteList(album: AlbumEntity) {
        albumDao.addToFavoriteList(album)
    }

    override suspend fun removeFromFavoriteList(album: AlbumEntity) {
        albumDao.removeFromFavoriteList(album.albumName, album.artistName)
    }

    override fun getFavoriteList(): List<Album> {
        return albumDao.getFavoriteList().map { albumEntityToAlbumMapper.invoke(it) }
    }

    override fun getOneFavoriteAlbum(
        albumParams: AlbumParams
    ): AlbumEntity {
        return albumDao.getOneFavoriteAlbum(albumParams.artistName, albumParams.albumName)
    }
}