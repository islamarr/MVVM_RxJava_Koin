package com.islam.music.features.search.data.remote.api

import com.islam.music.features.search.domain.entites.ArtistResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchAPIService {

    @GET("?method=artist.search")
    fun searchByArtist(
        @Query("artist") artist: String,
        @Query("page") page: Int
    ): Single<ArtistResponse>

}