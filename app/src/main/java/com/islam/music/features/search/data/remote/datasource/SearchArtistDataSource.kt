package com.islam.music.features.search.data.remote.datasource

import com.islam.music.features.search.domain.entites.ArtistResponse

interface SearchArtistDataSource {
    suspend fun searchArtist(query: String, page: Int): ArtistResponse
}