package com.islam.music.features.main_screen.domain.usecases

import com.islam.music.common.data.DataResponse
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import com.islam.music.features.main_screen.presentation.viewmodel.MainScreenStates
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MainScreenUseCase @Inject constructor(private val repository: AlbumDetailsRepository) {

    suspend fun execute(): MainScreenStates {
        return when (val response = repository.getFavoriteList()) {
            is DataResponse.Success -> {
                response.data?.let {
                    if (it.isEmpty()) MainScreenStates.EmptySavedList else
                        MainScreenStates.SavedListLoaded(it)
                } ?: MainScreenStates.ShowErrorMessage()
            }
            is DataResponse.Failure -> {
                response.reason?.let {
                    MainScreenStates.ShowErrorMessage(response.reason)
                } ?: MainScreenStates.ShowErrorMessage()
            }
        }

    }

}