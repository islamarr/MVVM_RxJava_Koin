package com.islam.music.features.search.data.remote.datasource

import com.islam.music.features.search.data.remote.api.SearchAPIService
import com.islam.music.features.search.domain.entites.ArtistResponse
import io.reactivex.rxjava3.core.Single

class SearchArtistRemoteDataSourceImpl (private val searchAPIService: SearchAPIService) :
    SearchArtistRemoteDataSource {
    override fun searchArtist(query: String, page: Int): Single<ArtistResponse> {
        return searchAPIService.searchByArtist(query, page)
    }
}