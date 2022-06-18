package com.islam.music.features.search.data.remote.datasource

import com.islam.music.features.search.domain.entites.ArtistResponse
import io.reactivex.rxjava3.core.Single

interface SearchArtistDataSource {
    fun searchArtist(query: String, page: Int): Single<ArtistResponse>
}