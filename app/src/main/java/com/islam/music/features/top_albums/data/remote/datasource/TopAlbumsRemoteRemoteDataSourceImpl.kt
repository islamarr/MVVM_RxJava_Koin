package com.islam.music.features.top_albums.data.remote.datasource

import com.islam.music.features.top_albums.data.remote.api.TopAlbumsAPIService
import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class TopAlbumsRemoteRemoteDataSourceImpl @Inject constructor(private val topAlbumsAPIService: TopAlbumsAPIService) :
    TopAlbumsRemoteDataSource {
    override fun getTopAlbums(artistName: String): Single<TopAlbumsResponse> =
        topAlbumsAPIService.getTopAlbums(artistName)
}