package com.islam.music.features.album_details.domain.usecases

import com.islam.music.common.data.DataResponse
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class AlbumDetailsUseCaseTest {
    private lateinit var useCase: AlbumDetailsUseCase

    @Mock
    private lateinit var repository: AlbumDetailsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = AlbumDetailsUseCase(repository)
    }

    @Test
    fun `when execute useCase return AlbumDetailsData State`() =
        runBlocking {

            val albumParams = AlbumParams(artistName = "artist", albumName = "album")
            val albumEntity = AlbumEntity(trackList = listOf())

            whenever(repository.getAlbumDetails(albumParams)).thenReturn(
                DataResponse.Success(
                    albumEntity
                )
            )

            val actual = useCase.execute(albumParams)
            val expected = AlbumDetailsStates.AlbumDetailsData(albumEntity)

            assertEquals(actual, expected)
        }

    @Test
    fun `test failure Response`() = runBlocking {
        val albumParams = AlbumParams(artistName = "artist", albumName = "album")
        val response = DataResponse.Failure<AlbumEntity>("reason")
        whenever(repository.getAlbumDetails(albumParams)).thenReturn(response)

        val actual = useCase.execute(albumParams)
        val expected = AlbumDetailsStates.ShowErrorMessage(response.reason)

        assertEquals(actual, expected)
    }

    @Test
    fun `test failure Response with null reason`() = runBlocking {
        val albumParams = AlbumParams(artistName = "artist", albumName = "album")
        val response = DataResponse.Failure<AlbumEntity>()
        whenever(repository.getAlbumDetails(albumParams)).thenReturn(response)

        val actual = useCase.execute(albumParams)
        val expected = AlbumDetailsStates.ShowErrorMessage()

        assertEquals(actual, expected)
    }

    @Test
    fun `when get null response return ShowErrorMessage`() = runBlocking {
        val albumParams = AlbumParams(artistName = "artist", albumName = "album")
        whenever(repository.getAlbumDetails(albumParams)).thenReturn(DataResponse.Success(null))

        val actual = useCase.execute(albumParams)
        val expected = AlbumDetailsStates.ShowErrorMessage()

        assertEquals(actual, expected)
    }

}