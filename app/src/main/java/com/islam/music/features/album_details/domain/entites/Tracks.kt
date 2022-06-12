package com.islam.music.features.album_details.domain.entites

import com.squareup.moshi.Json


data class Tracks(
    @field:Json(name = "track")
    val trackList: List<Track> = listOf()
)