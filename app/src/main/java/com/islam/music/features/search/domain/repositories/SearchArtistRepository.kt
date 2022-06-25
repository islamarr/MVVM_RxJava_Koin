package com.islam.music.features.search.domain.repositories

import com.islam.music.common.data.DataResponse
import com.islam.music.features.search.domain.entites.ArtistResponse

interface SearchArtistRepository {
    suspend fun searchArtist(query: String, page: Int) : DataResponse<ArtistResponse>
}