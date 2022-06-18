package com.islam.music.features.album_details.data.repositories

import com.islam.music.common.data.DataResponse
import com.islam.music.common.data.SafeServiceCall
import com.islam.music.features.album_details.data.db.datasource.AlbumDetailsLocalDataSource
import com.islam.music.features.album_details.data.remote.datasource.AlbumDetailsRemoteDataSource
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import com.islam.music.features.top_albums.domain.entites.Album
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AlbumDetailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: AlbumDetailsRemoteDataSource,
    private val localDataSource: AlbumDetailsLocalDataSource
) : AlbumDetailsRepository {

    override suspend fun getAlbumDetails(albumParams: AlbumParams): DataResponse<AlbumEntity> {
        return object : SafeServiceCall<AlbumEntity>(
            apiCall = { remoteDataSource.getAlbumDetails(albumParams) },
            cacheCall = { localDataSource.getOneFavoriteAlbum(albumParams) }
        ) {}.safeCall()
    }

    override suspend fun addToFavoriteList(album: AlbumEntity) {
        localDataSource.addToFavoriteList(album)
    }

    override suspend fun removeFromFavoriteList(album: AlbumEntity) {
        localDataSource.removeFromFavoriteList(album)
    }

    override fun getFavoriteList(): Single<List<Album>> {
        return localDataSource.getFavoriteList()
    }

    override suspend fun getOneFavoriteAlbum(albumParams: AlbumParams): DataResponse<AlbumEntity> {
        return object : SafeServiceCall<AlbumEntity>(
            cacheCall = { localDataSource.getOneFavoriteAlbum(albumParams) }
        ) {}.safeCall()
    }

}