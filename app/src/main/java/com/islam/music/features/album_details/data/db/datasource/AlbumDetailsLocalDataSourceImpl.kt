package com.islam.music.features.album_details.data.db.datasource

import com.islam.music.features.album_details.data.db.AlbumDao
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.main_screen.data.AlbumEntityToAlbumMapper
import com.islam.music.features.top_albums.domain.entites.Album
import io.reactivex.rxjava3.core.Observable.fromIterable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class AlbumDetailsLocalDataSourceImpl @Inject constructor(
    private val albumDao: AlbumDao,
    private val albumEntityToAlbumMapper: AlbumEntityToAlbumMapper
) :
    AlbumDetailsLocalDataSource {
    override fun addToFavoriteList(album: AlbumEntity) {
        albumDao.addToFavoriteList(album)
    }

    override fun removeFromFavoriteList(album: AlbumEntity) {
        albumDao.removeFromFavoriteList(album.albumName, album.artistName)
    }

    override fun getFavoriteList(): Single<List<Album>> {
        return albumDao.getFavoriteList().flatMap { list ->
            fromIterable(list).map { item -> albumEntityToAlbumMapper.invoke(item) }.toList()
        }
    }

    override fun getOneFavoriteAlbum(
        albumParams: AlbumParams
    ): Single<AlbumEntity> {
        return albumDao.getOneFavoriteAlbum(albumParams.artistName, albumParams.albumName)
    }
}