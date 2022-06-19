package com.islam.music.features.main_screen.domain.usecases

import com.islam.music.features.album_details.domain.repositories.AlbumDetailsRepository
import io.reactivex.rxjava3.core.Single

class MainScreenUseCase(private val repository: AlbumDetailsRepository) {

    fun execute(): Single<SavedListLoaded> { //TODO fix unit test for all project
        return repository.getFavoriteList().map {
            SavedListLoaded(it)
        }
    }

}