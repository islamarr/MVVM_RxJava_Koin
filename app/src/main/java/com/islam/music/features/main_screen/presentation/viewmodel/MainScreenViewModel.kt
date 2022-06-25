package com.islam.music.features.main_screen.presentation.viewmodel

import com.islam.music.common.view.BaseViewModel
import com.islam.music.features.main_screen.domain.usecases.MainScreenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val useCase: MainScreenUseCase) :
    BaseViewModel<MainScreenStates, MainScreenActions>(MainScreenStates.InitialState) {

    override fun handle(actions: MainScreenActions): Flow<MainScreenStates> = flow {
        when (actions) {
            is MainScreenActions.LoadAllSavedAlbums -> {
                emit(MainScreenStates.Loading)
                emit(useCase.execute())
            }
        }
    }

}