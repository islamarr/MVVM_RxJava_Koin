package com.islam.music.features.top_albums.domain.entites


import com.squareup.moshi.Json

data class Topalbums(
    @field:Json(name = "album")
    val albums: List<Album> = listOf(),
)