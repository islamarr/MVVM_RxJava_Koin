package com.islam.music.features.album_details.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.islam.music.MainCoroutineRule
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.album_details.domain.usecases.AlbumDetailsUseCase
import com.islam.music.features.album_details.domain.usecases.GetFavoriteUseCase
import com.islam.music.features.album_details.domain.usecases.SetFavoriteUseCase
import com.islam.music.features.search.domain.usecases.SearchArtistUseCase
import com.islam.music.features.search.presentation.viewmodel.SearchActions
import com.islam.music.features.search.presentation.viewmodel.SearchStates
import com.islam.music.features.search.presentation.viewmodel.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
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
class AlbumDetailsViewModelTest {

    private lateinit var viewModel: AlbumDetailsViewModel

    @Mock
    private lateinit var albumDetailsUseCase: AlbumDetailsUseCase

    @Mock
    private lateinit var getFavoriteUseCase: GetFavoriteUseCase

    @Mock
    private lateinit var setFavoriteUseCase: SetFavoriteUseCase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = AlbumDetailsViewModel(albumDetailsUseCase, getFavoriteUseCase, setFavoriteUseCase)
    }

    @Test
    fun `when load Album details return Loading State as a first result`() = runBlocking {
        val albumParams = AlbumParams(artistName = "artist", albumName = "album")

        val actual = viewModel.handle(AlbumDetailsActions.AlbumDetailsAction(albumParams)).first()
        val expected = AlbumDetailsStates.Loading

        assertEquals(actual, expected)
    }

    @Test
    fun `when load Album details return AlbumDetailsData State as a second result`() = runBlocking {
        val albumParams = AlbumParams(artistName = "artist", albumName = "album")
        val albumEntity = AlbumEntity(trackList = listOf())
        whenever(albumDetailsUseCase.execute(albumParams)).thenReturn(AlbumDetailsStates.AlbumDetailsData(albumEntity))
        whenever(getFavoriteUseCase.execute(albumParams)).thenReturn(AlbumDetailsStates.SavedState(true))

        val actual = viewModel.handle(AlbumDetailsActions.AlbumDetailsAction(albumParams)).drop(1).first()
        val expected = AlbumDetailsStates.AlbumDetailsData(albumEntity)

        assertEquals(actual, expected)

        verify(albumDetailsUseCase, times(1)).execute(albumParams)
        verifyNoMoreInteractions(albumDetailsUseCase)

    }

    @Test
    fun `when load Album details return SavedState State as a third result`() = runBlocking {
        val albumParams = AlbumParams(artistName = "artist", albumName = "album")
        val albumEntity = AlbumEntity(trackList = listOf())
        whenever(albumDetailsUseCase.execute(albumParams)).thenReturn(AlbumDetailsStates.AlbumDetailsData(albumEntity))
        whenever(getFavoriteUseCase.execute(albumParams)).thenReturn(AlbumDetailsStates.SavedState(true))

        val actual = viewModel.handle(AlbumDetailsActions.AlbumDetailsAction(albumParams)).drop(2).first()
        val expected = AlbumDetailsStates.SavedState(true)

        assertEquals(actual, expected)
        verify(getFavoriteUseCase, times(1)).execute(albumParams)
        verifyNoMoreInteractions(getFavoriteUseCase)

    }

    @Test
    fun `when start viewModel return InitialState`() = runBlocking {
        val expected = AlbumDetailsStates.InitialState
        val actual = viewModel.state.first()

        assertEquals(expected, actual)
    }

    @Test
    fun `when dispatch viewModel return SavedState`() = runBlocking {
        val albumParams = AlbumParams(artistName = "artist", albumName = "album")
        val albumEntity = AlbumEntity(trackList = listOf())
        val action = AlbumDetailsActions.AlbumDetailsAction(albumParams)

        val job = launch {
            viewModel.state.collect()
        }
        whenever(albumDetailsUseCase.execute(albumParams)).thenReturn(AlbumDetailsStates.AlbumDetailsData(albumEntity))
        whenever(getFavoriteUseCase.execute(albumParams)).thenReturn(AlbumDetailsStates.SavedState(true))

        viewModel.dispatch(action)

        val actual = viewModel.state.first()
        val expected = AlbumDetailsStates.SavedState(true)

        assertEquals(expected, actual)

        job.cancel()
    }
}