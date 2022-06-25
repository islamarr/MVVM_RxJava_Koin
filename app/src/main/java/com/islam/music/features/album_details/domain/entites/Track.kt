package com.islam.music.features.album_details.domain.entites

import com.islam.music.features.search.domain.entites.Artist
import com.squareup.moshi.Json


data class Track(
    val artist: Artist = Artist(),
    @field:Json(name = "@attr")
    val attr: Attr = Attr(),
    val duration: Int? = null,
    val name: String? = null,
)

data class Attr(
    val rank: Int? = 0 // 1
)