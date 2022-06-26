package com.islam.music.features.album_details.data.repositories

import com.islam.music.TestAppCoroutineDispatchers
import com.islam.music.TestCoroutineDispatcherRule
import com.islam.music.common.data.DataResponse
import com.islam.music.features.album_details.data.db.datasource.AlbumDetailsLocalDataSource
import com.islam.music.features.album_details.data.remote.datasource.AlbumDetailsRemoteDataSource
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.search.domain.entites.Artist
import com.islam.music.features.top_albums.domain.entites.Album
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class AlbumDetailsRepositoryImplTest {

    @get:Rule
    val coroutineRule = TestCoroutineDispatcherRule()

    private lateinit var repository: AlbumDetailsRepositoryImpl

    @Mock
    private lateinit var remoteDataSource: AlbumDetailsRemoteDataSource

    @Mock
    private lateinit var localDataSource: AlbumDetailsLocalDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = AlbumDetailsRepositoryImpl(
            remoteDataSource,
            localDataSource,
            TestAppCoroutineDispatchers(coroutineRule.testDispatcher)
        )
    }

    @Test
    fun `test get Album Details in success statue`() = runTest {
        val albumParams = AlbumParams(artistName = "artist", albumName = "album")
        val albumEntity = AlbumEntity(trackList = listOf())
        whenever(remoteDataSource.getAlbumDetails(albumParams)).thenReturn(albumEntity)
        whenever(localDataSource.getOneFavoriteAlbum(albumParams)).thenReturn(albumEntity)

        val actual = repository.getAlbumDetails(albumParams)
        val expected = DataResponse.Success(albumEntity)

        assertEquals(expected, actual)
    }

    @Test
    fun `test get Album Details in failure statue`() = runTest {
        val albumParams = AlbumParams(artistName = "artist", albumName = "album")
        whenever(remoteDataSource.getAlbumDetails(albumParams)).thenReturn(null)
        whenever(localDataSource.getOneFavoriteAlbum(albumParams)).thenReturn(null)

        val actual = repository.getAlbumDetails(albumParams)
        val expected = DataResponse.Failure<AlbumEntity>()

        assertEquals(expected, actual)
    }

    @Test
    fun `test get Favorite List in success statue`() = runTest {
        val albumEntity = listOf(Album(Artist(), listOf(), "1", "album"))
        whenever(localDataSource.getFavoriteList()).thenReturn(albumEntity)

        val actual = repository.getFavoriteList()
        val expected = DataResponse.Success(albumEntity)

        assertEquals(expected, actual)
    }

    @Test
    fun `test get One Favorite Album in success statue`() = runTest {
        val albumParams = AlbumParams(artistName = "artist", albumName = "album")
        val albumEntity = AlbumEntity(trackList = listOf())
        whenever(localDataSource.getOneFavoriteAlbum(albumParams)).thenReturn(albumEntity)

        val actual = repository.getOneFavoriteAlbum(albumParams)
        val expected = DataResponse.Success(albumEntity)

        assertEquals(expected, actual)
    }

}