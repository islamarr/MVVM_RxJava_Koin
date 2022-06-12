package com.islam.music.features.top_albums.data.remote.datasource

import com.islam.music.features.top_albums.data.remote.api.TopAlbumsAPIService
import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse
import javax.inject.Inject

class TopAlbumsRemoteRemoteDataSourceImpl @Inject constructor(private val topAlbumsAPIService: TopAlbumsAPIService) :
    TopAlbumsRemoteDataSource {
    override suspend fun getTopAlbums(artistName: String): TopAlbumsResponse {
        return topAlbumsAPIService.getTopAlbums(artistName)
    }
}