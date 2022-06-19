package com.islam.music.features.main_screen.data

import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.search.domain.entites.Artist
import com.islam.music.features.search.domain.entites.Image
import com.islam.music.features.top_albums.domain.entites.Album

class AlbumEntityToAlbumMapper {

    operator fun invoke(albumEntity: AlbumEntity): Album {
        return Album(
            albumName = albumEntity.albumName ?: "",
            images = listOf(Image(albumEntity.coverImageUrl ?: "")),
            id = albumEntity.id.toString(),
            artist = Artist(name = albumEntity.artistName ?: "")
        )
    }

}