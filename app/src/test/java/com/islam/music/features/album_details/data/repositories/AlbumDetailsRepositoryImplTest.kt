package com.islam.music.features.album_details.data.repositories

import com.islam.music.common.data.DataResponse
import com.islam.music.features.album_details.data.db.datasource.AlbumDetailsLocalDataSource
import com.islam.music.features.album_details.data.remote.datasource.AlbumDetailsRemoteDataSource
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.search.domain.entites.Artist
import com.islam.music.features.top_albums.domain.entites.Album
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class AlbumDetailsRepositoryImplTest {

    private lateinit var repository: AlbumDetailsRepositoryImpl

    @Mock
    private lateinit var remoteDataSource: AlbumDetailsRemoteDataSource

    @Mock
    private lateinit var localDataSource: AlbumDetailsLocalDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = AlbumDetailsRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `test get Album Details in success statue`() = runBlocking {
        val albumParams = AlbumParams(artistName = "artist", albumName = "album")
        val albumEntity = AlbumEntity(trackList = listOf())
        whenever(remoteDataSource.getAlbumDetails(albumParams)).thenReturn(albumEntity)
        whenever(localDataSource.getOneFavoriteAlbum(albumParams)).thenReturn(albumEntity)

        val actual = repository.getAlbumDetails(albumParams)
        val expected = DataResponse.Success(albumEntity)

        assertEquals(expected, actual)
    }

    @Test
    fun `test get Album Details in failure statue`() = runBlocking {
        val albumParams = AlbumParams(artistName = "artist", albumName = "album")
        whenever(remoteDataSource.getAlbumDetails(albumParams)).thenReturn(null)
        whenever(localDataSource.getOneFavoriteAlbum(albumParams)).thenReturn(null)

        val actual = repository.getAlbumDetails(albumParams)
        val expected = DataResponse.Failure<AlbumEntity>()

        assertEquals(expected, actual)
    }

    @Test
    fun `test get Favorite List in success statue`() = runBlocking {
        val albumEntity = listOf(Album(Artist(), listOf(), "1", "album"))
        whenever(localDataSource.getFavoriteList()).thenReturn(albumEntity)

        val actual = repository.getFavoriteList()
        val expected = DataResponse.Success(albumEntity)

        assertEquals(expected, actual)
    }

    @Test
    fun `test get One Favorite Album in success statue`() = runBlocking {
        val albumParams = AlbumParams(artistName = "artist", albumName = "album")
        val albumEntity = AlbumEntity(trackList = listOf())
        whenever(localDataSource.getOneFavoriteAlbum(albumParams)).thenReturn(albumEntity)

        val actual = repository.getOneFavoriteAlbum(albumParams)
        val expected = DataResponse.Success(albumEntity)

        assertEquals(expected, actual)
    }

}