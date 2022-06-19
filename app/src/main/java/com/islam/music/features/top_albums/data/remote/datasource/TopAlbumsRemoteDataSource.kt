package com.islam.music.features.top_albums.data.remote.datasource

import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse
import io.reactivex.rxjava3.core.Single

interface TopAlbumsRemoteDataSource  {
    fun getTopAlbums(artistName: String) : Single<TopAlbumsResponse>
}