package com.islam.music.features.top_albums.presentation.viewmodel

import com.islam.music.common.view.BaseViewModel
import com.islam.music.features.top_albums.domain.usecases.TopAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class TopAlbumsViewModel @Inject constructor(private val useCase: TopAlbumsUseCase) :
    BaseViewModel<TopAlbumsStates, TopAlbumsActions>(TopAlbumsStates.InitialState) {

    override fun handle(actions: TopAlbumsActions): Flow<TopAlbumsStates> = flow {
        when (actions) {
            is TopAlbumsActions.LoadAllAlbums -> {
                emit(TopAlbumsStates.Loading)
                emit(useCase.execute(actions.artistName))
            }
        }
    }

}