package com.islam.music.features.top_albums.domain.repositories

import com.islam.music.common.data.DataResponse
import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse

interface TopAlbumsRepository {
    suspend fun getTopAlbums(artistName: String) : DataResponse<TopAlbumsResponse>
}