package com.islam.music.features.top_albums.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.islam.music.MainCoroutineRule
import com.islam.music.features.top_albums.domain.usecases.TopAlbumsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
class TopAlbumsViewModelTest {

    private lateinit var viewModel: TopAlbumsViewModel

    @Mock
    private lateinit var useCase: TopAlbumsUseCase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = TopAlbumsViewModel(useCase)
    }

    @Test
    fun `when load Albums return Loading State as a first result`() = runBlocking {
        val artistName = "artist"

        val actual = viewModel.handle(TopAlbumsActions.LoadAllAlbums(artistName)).first()
        val expected = TopAlbumsStates.Loading

        assertEquals(actual, expected)
    }

    @Test
    fun `when load Albums return Empty Top Albums List State as a second result`() = runBlocking {
        val artistName = "artist"
        whenever(useCase.execute(artistName)).thenReturn(TopAlbumsStates.EmptyTopAlbumsList)

        val actual = viewModel.handle(TopAlbumsActions.LoadAllAlbums(artistName)).drop(1).first()
        val expected = TopAlbumsStates.EmptyTopAlbumsList

        assertEquals(actual, expected)

        verify(useCase, times(1)).execute(artistName)
        verifyNoMoreInteractions(useCase)
    }

    @Test
    fun `when start viewModel return InitialState`() = runBlocking {
        val expected = TopAlbumsStates.InitialState
        val actual = viewModel.state.first()

        assertEquals(expected, actual)
    }

    @Test
    fun `when dispatch viewModel return Empty Top Albums List state`() = runBlocking {
        val artistName = "artist"
        val action = TopAlbumsActions.LoadAllAlbums(artistName)

        val job = launch {
            viewModel.state.collect()
        }
        whenever(useCase.execute(artistName)).thenReturn(TopAlbumsStates.EmptyTopAlbumsList)
        viewModel.dispatch(action)

        val actual = viewModel.state.first()
        val expected = TopAlbumsStates.EmptyTopAlbumsList

        assertEquals(expected, actual)
        job.cancel()
    }
}