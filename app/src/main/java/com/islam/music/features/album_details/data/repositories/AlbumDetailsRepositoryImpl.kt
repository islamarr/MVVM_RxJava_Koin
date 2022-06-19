package com.islam.music.features.album_details.data.repositories

import com.islam.music.features.album_details.data.db.datasource.AlbumDetailsLocalDataSource
import com.islam.music.features.album_details.data.remote.datasource.AlbumDetailsRemoteDataSource
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import com.islam.music.features.top_albums.domain.entites.Album
import io.reactivex.rxjava3.core.Single


class AlbumDetailsRepositoryImpl (
    private val remoteDataSource: AlbumDetailsRemoteDataSource,
    private val localDataSource: AlbumDetailsLocalDataSource
) : AlbumDetailsRepository {

    override fun getAlbumDetails(albumParams: AlbumParams): Single<AlbumEntity> {
        return remoteDataSource.getAlbumDetails(albumParams).onErrorResumeNext {
            localDataSource.getOneFavoriteAlbum(albumParams)
        }
    }

    override fun addToFavoriteList(album: AlbumEntity) {
        localDataSource.addToFavoriteList(album)
    }

    override fun removeFromFavoriteList(album: AlbumEntity) {
        localDataSource.removeFromFavoriteList(album)
    }

    override fun getFavoriteList(): Single<List<Album>> = localDataSource.getFavoriteList()

    override fun getOneFavoriteAlbum(albumParams: AlbumParams): Single<AlbumEntity> {
        return localDataSource.getOneFavoriteAlbum(albumParams)
    }

}