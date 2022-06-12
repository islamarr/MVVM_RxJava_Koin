package com.islam.music.features.main_screen.data

import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.Track
import com.islam.music.features.search.domain.entites.Artist
import com.islam.music.features.search.domain.entites.Image
import com.islam.music.features.top_albums.domain.entites.Album
import org.junit.Assert
import org.junit.Test

class AlbumEntityToAlbumMapperTest {

    private val albumMapper = AlbumEntityToAlbumMapper()

    @Test
    fun `test mapping`() {
        val albumEntity = AlbumEntity(
            "artistName", "https", "albumName",
            listOf(Track())
        )

        val actual = albumMapper.invoke(albumEntity)
        val expected = Album(
            Artist(name = albumEntity.artistName),
            images = listOf(Image(url = albumEntity.coverImageUrl.toString())),
            id = albumEntity.id.toString(),
            albumName = albumEntity.albumName.toString()
        )

        Assert.assertEquals(expected, actual)
    }

}