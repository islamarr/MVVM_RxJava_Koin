package com.islam.music.features.album_details.data.db

import com.islam.music.features.album_details.domain.entites.AlbumDetails
import com.islam.music.features.album_details.domain.entites.AlbumEntity

class AlbumDetailsToAlbumMapper {

    operator fun invoke(albumDetails: AlbumDetails): AlbumEntity {
        return AlbumEntity(
            artistName = albumDetails.artist ?: "",
            coverImageUrl = albumDetails.images?.get(0)?.url ?: "",
            albumName = albumDetails.name ?: "",
            trackList = albumDetails.tracks?.trackList ?: listOf()
        )
    }

}