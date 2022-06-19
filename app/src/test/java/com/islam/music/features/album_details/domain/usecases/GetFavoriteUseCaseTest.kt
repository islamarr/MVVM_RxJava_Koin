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

class GetFavoriteUseCaseTest {
    private lateinit var useCase: GetFavoriteUseCase

    @Mock
    private lateinit var repository: AlbumDetailsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetFavoriteUseCase(repository)
    }

    @Test
    fun `when execute useCase return SavedState`() = runBlocking {
        val albumParams = AlbumParams(artistName = "artist", albumName = "album")
        val albumEntity = AlbumEntity(trackList = listOf())
        whenever(repository.getOneFavoriteAlbum(albumParams)).thenReturn(
            DataResponse.Success(albumEntity)
        )

        val actual = useCase.execute(albumParams)
        val expected = AlbumDetailsStates.SavedState(true)

        assertEquals(actual, expected)
    }

    @Test
    fun `when failure Response return not SavedState`() = runBlocking {
        val albumParams = AlbumParams(artistName = "artist", albumName = "album")
        whenever(repository.getOneFavoriteAlbum(albumParams)).thenReturn(
            DataResponse.Failure()
        )

        val actual = useCase.execute(albumParams)
        val expected = AlbumDetailsStates.SavedState(false)

        assertEquals(actual, expected)
    }

}