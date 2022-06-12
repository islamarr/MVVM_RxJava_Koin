package com.islam.music.features.album_details.domain.usecases

import com.islam.music.common.data.DataResponse
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import com.islam.music.features.album_details.presentation.viewmodel.AlbumDetailsStates
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class AlbumDetailsUseCase @Inject constructor(private val repository: AlbumDetailsRepository) {

    suspend fun execute(albumParams: AlbumParams): AlbumDetailsStates {
        return when (val response = repository.getAlbumDetails(albumParams)) {

            is DataResponse.Success -> {
                response.data?.let {
                    AlbumDetailsStates.AlbumDetailsData(it)
                } ?: AlbumDetailsStates.ShowErrorMessage()
            }
            is DataResponse.Failure -> {
                response.reason?.let {
                    AlbumDetailsStates.ShowErrorMessage(response.reason)
                } ?: AlbumDetailsStates.ShowErrorMessage()
            }
        }

    }

}