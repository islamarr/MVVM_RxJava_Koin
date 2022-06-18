package com.islam.music.features.search.domain.repositories

import com.islam.music.features.search.domain.entites.ArtistResponse
import io.reactivex.rxjava3.core.Single

interface SearchArtistRepository {
    fun searchArtist(query: String, page: Int): Single<ArtistResponse>
}