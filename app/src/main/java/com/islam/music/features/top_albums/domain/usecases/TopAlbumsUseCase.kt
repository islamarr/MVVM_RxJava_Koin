package com.islam.music.features.top_albums.domain.usecases

import com.islam.music.features.top_albums.domain.entites.TopAlbumsListLoaded
import com.islam.music.features.top_albums.domain.repositories.TopAlbumsRepository
import io.reactivex.rxjava3.core.Single
import org.koin.core.annotation.Factory

@Factory
class TopAlbumsUseCase(private val repository: TopAlbumsRepository) {

    fun execute(artistName: String): Single<TopAlbumsListLoaded> =
        repository.getTopAlbums(artistName).map { TopAlbumsListLoaded(it.topAlbums.albums) }

}