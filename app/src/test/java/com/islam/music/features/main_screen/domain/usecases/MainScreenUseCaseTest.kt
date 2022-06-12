package com.islam.music.features.main_screen.domain.usecases

import com.islam.music.common.data.DataResponse
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import com.islam.music.features.main_screen.presentation.viewmodel.MainScreenStates
import com.islam.music.features.search.domain.entites.Artist
import com.islam.music.features.top_albums.domain.entites.Album
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class MainScreenUseCaseTest {
    private lateinit var useCase: MainScreenUseCase

    @Mock
    private lateinit var repository: AlbumDetailsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = MainScreenUseCase(repository)
    }

    @Test
    fun `when execute useCase return EmptySavedList State with Empty list response`() =
        runBlocking {

            whenever(repository.getFavoriteList()).thenReturn(DataResponse.Success(listOf()))

            val actual = useCase.execute()
            val expected = MainScreenStates.EmptySavedList

            assertEquals(actual, expected)
        }

    @Test
    fun `when get Favorite List response return SavedListLoaded result`() = runBlocking {
        val responseData = listOf(Album(Artist(), listOf(), "1", "album"))
        whenever(repository.getFavoriteList()).thenReturn(
            DataResponse.Success(responseData)
        )

        val actual = useCase.execute()
        val expected = MainScreenStates.SavedListLoaded(responseData)

        assertEquals(actual, expected)
    }

    @Test
    fun `test failure Response`() = runBlocking {
        val response = DataResponse.Failure<List<Album>>("reason")

        whenever(repository.getFavoriteList()).thenReturn(response)

        val actual = useCase.execute()
        val expected = MainScreenStates.ShowErrorMessage(response.reason)

        assertEquals(actual, expected)
    }

    @Test
    fun `test failure Response with null reason`() = runBlocking {
        val response = DataResponse.Failure<List<Album>>()

        whenever(repository.getFavoriteList()).thenReturn(response)

        val actual = useCase.execute()
        val expected = MainScreenStates.ShowErrorMessage()

        assertEquals(actual, expected)
    }

    @Test
    fun `when get null response return ShowErrorMessage`() = runBlocking {
        whenever(repository.getFavoriteList()).thenReturn(DataResponse.Success(null))

        val actual = useCase.execute()
        val expected = MainScreenStates.ShowErrorMessage()

        assertEquals(actual, expected)
    }
}
