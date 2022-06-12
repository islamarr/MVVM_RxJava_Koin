package com.islam.music.features.search.domain.entites

import com.squareup.moshi.Json

data class Image(
    @field:Json(name = "#text")
    val url: String
)