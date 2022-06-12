package com.islam.music.features.search.domain.entites


import com.squareup.moshi.Json

data class OpensearchQuery(
    val role: String, // request
    val searchTerms: String, // cher
    val startPage: String, // 1
    @field:Json(name = "#text")
    val text: String
)