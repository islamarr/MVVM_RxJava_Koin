package com.islam.music.features.top_albums.domain.usecases

import com.islam.music.common.data.DataResponse
import com.islam.music.features.search.domain.entites.Artist
import com.islam.music.features.top_albums.domain.entites.Album
import com.islam.music.features.top_albums.domain.entites.TopAlbumsResponse
import com.islam.music.features.top_albums.domain.entites.Topalbums
import com.islam.music.features.top_albums.domain.repositories.TopAlbumsRepository
import com.islam.music.features.top_albums.presentation.viewmodel.TopAlbumsStates
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class TopAlbumsUseCaseTest {
    private lateinit var useCase: TopAlbumsUseCase

    @Mock
    private lateinit var repository: TopAlbumsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = TopAlbumsUseCase(repository)
    }

    @Test
    fun `when execute useCase return Empty Top Albums List State with Empty list response`() =
        runBlocking {

            val artistName = "artist"
            whenever(repository.getTopAlbums(artistName)).thenReturn(
                DataResponse.Success(
                    TopAlbumsResponse(Topalbums(listOf()))
                )
            )

            val actual = useCase.execute(artistName)
            val expected = TopAlbumsStates.EmptyTopAlbumsList

            assertEquals(actual, expected)
        }

    @Test
    fun `when get Top album list response return Top Albums List Loaded State`() = runBlocking {
        val artistName = "artist"
        val responseData = Topalbums(listOf(Album(Artist(), listOf(), "1", "album")))
        whenever(repository.getTopAlbums(artistName)).thenReturn(
            DataResponse.Success(TopAlbumsResponse(responseData))
        )

        val actual = useCase.execute(artistName)
        val expected = TopAlbumsStates.TopAlbumsListLoaded(responseData.albums)

        assertEquals(actual, expected)
    }

    @Test
    fun `test failure Response`() = runBlocking {
        val artistName = "artist"
        val response = DataResponse.Failure<TopAlbumsResponse>("reason")

        whenever(repository.getTopAlbums(artistName)).thenReturn(response)

        val actual = useCase.execute(artistName)
        val expected = TopAlbumsStates.ShowErrorMessage(response.reason)

        assertEquals(actual, expected)
    }

    @Test
    fun `test failure Response with null reason`() = runBlocking {
        val artistName = "artist"
        val response = DataResponse.Failure<TopAlbumsResponse>()

        whenever(repository.getTopAlbums(artistName)).thenReturn(response)

        val actual = useCase.execute(artistName)
        val expected = TopAlbumsStates.ShowErrorMessage()

        assertEquals(actual, expected)
    }

    @Test
    fun `when get null response return ShowErrorMessage`() = runBlocking {
        val artistName = "artist"
        whenever(repository.getTopAlbums(artistName)).thenReturn(DataResponse.Success(null))

        val actual = useCase.execute(artistName)
        val expected = TopAlbumsStates.ShowErrorMessage()

        assertEquals(actual, expected)
    }

}