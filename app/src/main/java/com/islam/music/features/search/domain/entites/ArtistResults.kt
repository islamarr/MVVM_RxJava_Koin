package com.islam.music.features.search.domain.entites


import com.squareup.moshi.Json

data class ArtistResults(
    @field:Json(name = "artistmatches")
    val artists: Artistmatches,
    @field:Json(name = "opensearch:itemsPerPage")
    val itemsPerPage: String, // 30
    @field:Json(name = "opensearch:Query")
    val query: OpensearchQuery,
    @field:Json(name = "opensearch:startIndex")
    val startIndex: String, // 0
    @field:Json(name = "opensearch:totalResults")
    val totalResults: String // 73593
)