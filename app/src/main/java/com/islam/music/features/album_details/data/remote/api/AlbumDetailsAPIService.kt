package com.islam.music.features.album_details.data.remote.api

import com.islam.music.features.album_details.domain.entites.AlbumDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumDetailsAPIService {

    @GET("?method=album.getinfo&artist=Cher&album=Believe")
    suspend fun getAlbumDetails(
        @Query("artist") artistName: String,
        @Query("album") albumName: String
    ): AlbumDetailsResponse

}