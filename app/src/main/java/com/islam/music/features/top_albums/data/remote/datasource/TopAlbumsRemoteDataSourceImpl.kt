package com.islam.music.features.top_albums.data.remote.datasource

import com.islam.music.features.top_albums.data.remote.api.TopAlbumsAPIService
import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse
import io.reactivex.rxjava3.core.Single

class TopAlbumsRemoteDataSourceImpl(private val topAlbumsAPIService: TopAlbumsAPIService) :
    TopAlbumsRemoteDataSource {
    override fun getTopAlbums(artistName: String): Single<TopAlbumsResponse> =
        topAlbumsAPIService.getTopAlbums(artistName)
}