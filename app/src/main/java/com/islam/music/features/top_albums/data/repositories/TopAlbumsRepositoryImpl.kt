package com.islam.music.features.top_albums.data.repositories

import com.islam.music.features.top_albums.data.remote.datasource.TopAlbumsRemoteDataSource
import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse
import com.islam.music.features.top_albums.domain.repositories.TopAlbumsRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class TopAlbumsRepositoryImpl @Inject constructor(private val dataSource: TopAlbumsRemoteDataSource) :
    TopAlbumsRepository {
    override fun getTopAlbums(artistName: String): Single<TopAlbumsResponse> =
        dataSource.getTopAlbums(artistName)
}