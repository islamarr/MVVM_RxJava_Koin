package com.islam.music.features.top_albums.data.repositories

import com.islam.music.common.data.DataResponse
import com.islam.music.features.top_albums.data.remote.datasource.TopAlbumsRemoteDataSource
import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class TopAlbumsRepositoryImplTest {

    private lateinit var repository: TopAlbumsRepositoryImpl

    @Mock
    private lateinit var remoteDataSource: TopAlbumsRemoteDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = TopAlbumsRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `test get Top Albums in success statue`() = runBlocking {
        val artistName = "artist"
        val response = TopAlbumsResponse()
        whenever(remoteDataSource.getTopAlbums(artistName)).thenReturn(response)

        val actual = repository.getTopAlbums(artistName)
        val expected = DataResponse.Success(response)

        assertEquals(expected, actual)
    }

    @Test
    fun `test get Top Albums in failure statue`() = runBlocking {
        val artistName = "artist"
        whenever(remoteDataSource.getTopAlbums(artistName)).thenReturn(null)

        val actual = repository.getTopAlbums(artistName)
        val expected = DataResponse.Failure<TopAlbumsResponse>()

        assertEquals(expected, actual)
    }
}