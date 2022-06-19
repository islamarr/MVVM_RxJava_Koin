package com.islam.music.features.top_albums.domain.usecases

import com.islam.music.features.top_albums.domain.entites.TopAlbumsListLoaded
import com.islam.music.features.top_albums.domain.repositories.TopAlbumsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@ViewModelScoped
class TopAlbumsUseCase @Inject constructor(private val repository: TopAlbumsRepository) {

    fun execute(artistName: String): Single<TopAlbumsListLoaded> =
        repository.getTopAlbums(artistName).map { TopAlbumsListLoaded(it.topAlbums.albums) }

}