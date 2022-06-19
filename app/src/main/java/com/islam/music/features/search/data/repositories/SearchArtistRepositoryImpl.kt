package com.islam.music.features.search.data.repositories

import com.islam.music.features.search.data.remote.datasource.SearchArtistRemoteDataSource
import com.islam.music.features.search.domain.entites.ArtistResponse
import com.islam.music.features.search.domain.repositories.SearchArtistRepository
import io.reactivex.rxjava3.core.Single

class SearchArtistRepositoryImpl (private val dataSource: SearchArtistRemoteDataSource) :
    SearchArtistRepository {
    override fun searchArtist(query: String, page: Int): Single<ArtistResponse> {
        return dataSource.searchArtist(query, page)
    }
}