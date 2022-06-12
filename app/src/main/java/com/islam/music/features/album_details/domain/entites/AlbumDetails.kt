package com.islam.music.features.album_details.domain.entites


import com.islam.music.features.search.domain.entites.Image
import com.squareup.moshi.Json

data class AlbumDetails(
    val artist: String? = null,
    @field:Json(name = "image")
    val images: List<Image>? = listOf(),
    @field:Json(name = "mbid")
    val id: String? = null,
    val name: String? = null,
    val tracks: Tracks? = Tracks(),
)