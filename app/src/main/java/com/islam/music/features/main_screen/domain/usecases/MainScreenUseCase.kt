package com.islam.music.features.main_screen.domain.usecases

import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import com.islam.music.features.main_screen.data.AlbumEntityToAlbumMapper
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@ViewModelScoped
class MainScreenUseCase @Inject constructor(private val repository: AlbumDetailsRepository) {

    fun execute(): Single<SavedListLoaded> { //TODO fix unit test for all project
        return repository.getFavoriteList().map {
            SavedListLoaded(it)
        }
    }

}