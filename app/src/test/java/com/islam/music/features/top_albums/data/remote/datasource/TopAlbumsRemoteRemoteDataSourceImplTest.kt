package com.islam.music.features.top_albums.data.remote.datasource

import com.islam.music.features.top_albums.data.remote.api.TopAlbumsAPIService
import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class TopAlbumsRemoteRemoteDataSourceImplTest {
    private lateinit var dataSource: TopAlbumsRemoteDataSourceImpl

    @Mock
    private lateinit var apiService: TopAlbumsAPIService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        dataSource = TopAlbumsRemoteDataSourceImpl(apiService)
    }

    @Test
    fun `test return success response`() = runBlocking {
        val artistName = "artist"
        val response = TopAlbumsResponse()
        whenever(apiService.getTopAlbums(artistName)).thenReturn(response)

        assertEquals(response, dataSource.getTopAlbums(artistName))
    }
}