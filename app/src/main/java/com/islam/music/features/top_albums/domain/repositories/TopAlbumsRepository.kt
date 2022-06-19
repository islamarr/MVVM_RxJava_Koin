package com.islam.music.features.top_albums.domain.repositories

import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse
import io.reactivex.rxjava3.core.Single

interface TopAlbumsRepository {
    fun getTopAlbums(artistName: String): Single<TopAlbumsResponse>
}