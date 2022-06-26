package com.islam.music.features.main_screen.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.islam.music.TestCoroutineDispatcherRule
import com.islam.music.features.main_screen.domain.usecases.MainScreenUseCase
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
class MainScreenViewModelTest {

    private lateinit var viewModel: MainScreenViewModel

    @Mock
    private lateinit var useCase: MainScreenUseCase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = TestCoroutineDispatcherRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MainScreenViewModel(useCase)
    }

    @Test
    fun `when load Saved Albums return Loading State as a first result`() = runTest {
        val actual = viewModel.handle(MainScreenActions.LoadAllSavedAlbums).first()
        val expected = MainScreenStates.Loading

        assertEquals(actual, expected)
    }

    @Test
    fun `when load Saved Albums return LoadAllSavedAlbums State as a second result`() =
        runTest {
            whenever(useCase.execute()).thenReturn(MainScreenStates.EmptySavedList)

            val actual = viewModel.handle(MainScreenActions.LoadAllSavedAlbums).drop(1).first()
            val expected = MainScreenStates.EmptySavedList

            assertEquals(actual, expected)

            verify(useCase, times(1)).execute()
            verifyNoMoreInteractions(useCase)
        }

    @Test
    fun `when start viewModel return InitialState`() = runTest {
        val expected = MainScreenStates.InitialState
        val actual = viewModel.state.first()

        assertEquals(expected, actual)
    }

    @Test
    fun `when dispatch viewModel return EmptySavedList state`() = runTest {
        val action = MainScreenActions.LoadAllSavedAlbums

        val job = launch {
            viewModel.state.collect()
        }
        whenever(useCase.execute()).thenReturn(MainScreenStates.EmptySavedList)
        viewModel.dispatch(action)

        val actual = viewModel.state.first()
        val expected = MainScreenStates.EmptySavedList

        assertEquals(expected, actual)
        job.cancel()
    }
}