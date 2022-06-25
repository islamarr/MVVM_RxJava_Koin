package com.islam.music.features.top_albums.data.remote.api

import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TopAlbumsAPIService {

    @GET("?method=artist.gettopalbums")
    suspend fun getTopAlbums(@Query("artist") artistName: String): TopAlbumsResponse

}