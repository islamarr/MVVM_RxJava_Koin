package com.islam.music.features.album_details.domain.usecases

import com.islam.music.common.data.DataResponse
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import com.islam.music.features.album_details.presentation.viewmodel.AlbumDetailsStates
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetFavoriteUseCase @Inject constructor(private val repository: AlbumDetailsRepository) {

    suspend fun execute(albumParams: AlbumParams): AlbumDetailsStates {
        return when (repository.getOneFavoriteAlbum(albumParams)) {
            is DataResponse.Success -> {
                AlbumDetailsStates.SavedState(true)
            }
            is DataResponse.Failure -> {
                AlbumDetailsStates.SavedState(false)
            }
        }
    }

}