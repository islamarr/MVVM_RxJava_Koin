package com.islam.music.features.search.data.repositories

import com.islam.music.common.AppCoroutineDispatchers
import com.islam.music.common.data.DataResponse
import com.islam.music.common.data.SafeServiceCall
import com.islam.music.features.search.data.remote.datasource.SearchArtistDataSource
import com.islam.music.features.search.domain.entites.ArtistResponse
import com.islam.music.features.search.domain.repositories.SearchArtistRepository
import javax.inject.Inject

class SearchArtistRepositoryImpl @Inject constructor(
    private val dataSource: SearchArtistDataSource,
    private val dispatchers: AppCoroutineDispatchers
) :
    SearchArtistRepository {
    override suspend fun searchArtist(query: String, page: Int): DataResponse<ArtistResponse> {
        return object : SafeServiceCall<ArtistResponse>(
            dispatcher = dispatchers.io,
            apiCall = { dataSource.searchArtist(query, page) },
        ) {}.safeCall()
    }
}