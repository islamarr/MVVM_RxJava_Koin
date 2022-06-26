package com.islam.music.features.search.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.islam.music.TestCoroutineDispatcherRule
import com.islam.music.features.search.domain.usecases.SearchArtistUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModel

    @Mock
    private lateinit var useCase: SearchArtistUseCase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = TestCoroutineDispatcherRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = SearchViewModel(useCase)
    }

    @Test
    fun `when load Albums return Loading State as a first result`() = runTest {
        val artistName = "artist"

        val actual = viewModel.handle(SearchActions.SearchArtistByName(artistName)).first()
        val expected = SearchStates.Loading

        assertEquals(actual, expected)
    }

    @Test
    fun `when load Albums return EmptyArtistList State as a second result`() = runTest {
        val artistName = "artist"
        val isLoadMore = false
        whenever(useCase.execute(artistName, isLoadMore)).thenReturn(SearchStates.EmptyArtistList)

        val actual = viewModel.handle(SearchActions.SearchArtistByName(artistName)).drop(1).first()
        val expected = SearchStates.EmptyArtistList

        assertEquals(actual, expected)

        verify(useCase, times(1)).execute(artistName, isLoadMore)
        verifyNoMoreInteractions(useCase)
    }

    @Test
    fun `when start viewModel return InitialState`() = runTest {
        val expected = SearchStates.InitialState
        val actual = viewModel.state.first()

        assertEquals(expected, actual)
    }

    @Test
    fun `when dispatch viewModel return EmptyArtistList state`() = runTest {
        val artistName = "artist"
        val isLoadMore = false
        val action = SearchActions.SearchArtistByName(artistName)

        val job = launch {
            viewModel.state.collect()
        }
        whenever(useCase.execute(artistName, isLoadMore)).thenReturn(SearchStates.EmptyArtistList)
        viewModel.dispatch(action)

        val actual = viewModel.state.first()
        val expected = SearchStates.EmptyArtistList

        assertEquals(expected, actual)
        job.cancel()
    }
}