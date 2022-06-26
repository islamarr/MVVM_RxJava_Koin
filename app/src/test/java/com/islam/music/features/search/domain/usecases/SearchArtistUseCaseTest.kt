package com.islam.music.features.search.domain.usecases

import com.islam.music.common.data.DataResponse
import com.islam.music.features.album_details.presentation.viewmodel.AlbumDetailsStates
import com.islam.music.features.search.domain.entites.ArtistResponse
import com.islam.music.features.search.domain.entites.ArtistResults
import com.islam.music.features.search.domain.entites.Artistmatches
import com.islam.music.features.search.domain.entites.OpensearchQuery
import com.islam.music.features.search.domain.repositories.SearchArtistRepository
import com.islam.music.features.search.presentation.viewmodel.SearchStates
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
class SearchArtistUseCaseTest {

    private lateinit var searchArtistUseCase: SearchArtistUseCase

    @Mock
    private lateinit var repository: SearchArtistRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        searchArtistUseCase = SearchArtistUseCase(repository)
    }

    @Test
    fun `when execute useCase with response empty artist list return EmptyArtistList state`() = runTest {
        val query = "query"
        val isLoadMore = false
        val page = 1
        val response =
            ArtistResponse(
                ArtistResults(
                    Artistmatches(listOf()), "10",
                    OpensearchQuery("", "", "", ""), "1", "0"
                )
            )
        whenever(repository.searchArtist(query, page)).thenReturn(
            DataResponse.Success(response)
        )

        val actual = searchArtistUseCase.execute(query, isLoadMore)
        val expected = SearchStates.EmptyArtistList

        Assert.assertEquals(actual, expected)
    }
}