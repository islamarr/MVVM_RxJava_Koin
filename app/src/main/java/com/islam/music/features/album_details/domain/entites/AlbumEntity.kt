package com.islam.music.features.album_details.domain.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.islam.music.common.CURRENT_ALBUM_ID


@Entity(tableName = "album")
data class AlbumEntity(
    val artistName: String? = null,
    val coverImageUrl: String? = null,
    val albumName: String? = null,
    val trackList: List<Track> = listOf()
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = CURRENT_ALBUM_ID
}