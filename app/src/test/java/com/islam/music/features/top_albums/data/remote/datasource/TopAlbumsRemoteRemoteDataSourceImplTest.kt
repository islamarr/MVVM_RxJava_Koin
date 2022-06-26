package com.islam.music.features.top_albums.data.remote.datasource

import com.islam.music.features.top_albums.data.remote.api.TopAlbumsAPIService
import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class TopAlbumsRemoteRemoteDataSourceImplTest {
    private lateinit var dataSource: TopAlbumsRemoteRemoteDataSourceImpl

    @Mock
    private lateinit var apiService: TopAlbumsAPIService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        dataSource = TopAlbumsRemoteRemoteDataSourceImpl(apiService)
    }

    @Test
    fun `test return success response`() = runTest {
        val artistName = "artist"
        val response = TopAlbumsResponse()
        whenever(apiService.getTopAlbums(artistName)).thenReturn(response)

        assertEquals(response, dataSource.getTopAlbums(artistName))
    }
}