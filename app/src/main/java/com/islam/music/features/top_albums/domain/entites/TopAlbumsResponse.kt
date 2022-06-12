package com.islam.music.features.top_albums.domain.entites

import com.squareup.moshi.Json

data class TopAlbumsResponse(
    @field:Json(name = "topalbums")
    val topAlbums: Topalbums = Topalbums()
)