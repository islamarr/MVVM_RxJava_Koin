package com.islam.music.features.album_details.data.remote.datasource

import com.islam.music.features.album_details.data.db.AlbumDetailsToAlbumMapper
import com.islam.music.features.album_details.data.remote.api.AlbumDetailsAPIService
import com.islam.music.features.album_details.domain.entites.AlbumDetails
import com.islam.music.features.album_details.domain.entites.AlbumDetailsResponse
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.album_details.domain.entites.Tracks
import com.islam.music.features.search.domain.entites.Image
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class AlbumDetailsRemoteDataSourceImplTest {

    private lateinit var dataSource: AlbumDetailsRemoteDataSourceImpl
    private val mapper = AlbumDetailsToAlbumMapper()

    @Mock
    private lateinit var apiService: AlbumDetailsAPIService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        dataSource = AlbumDetailsRemoteDataSourceImpl(apiService, mapper)
    }

    @Test
    fun `whe get Album details return mapped album entity`() = runTest {
        val albumParams = AlbumParams(artistName = "artist", albumName = "album")
        val response = AlbumDetailsResponse(AlbumDetails("", listOf(Image("")), "", "", Tracks()))
        whenever(
            apiService.getAlbumDetails(
                albumParams.artistName,
                albumParams.albumName
            )
        ).thenReturn(response)

        val actual = dataSource.getAlbumDetails(albumParams)
        val expected = mapper.invoke(response.album)

        Assert.assertEquals(expected, actual)
    }

}