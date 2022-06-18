package com.islam.music.features.search.domain.usecases

import com.islam.music.features.search.domain.entites.Artist
import com.islam.music.features.search.domain.entites.ArtistListLoaded
import com.islam.music.features.search.domain.repositories.SearchArtistRepository
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@ViewModelScoped
class SearchArtistUseCase @Inject constructor(private val repository: SearchArtistRepository) {

    private var isReachBottom = false
    private var currentPage = 1
    private var artistList = mutableListOf<Artist>()

    fun execute(query: String, isLoadMore: Boolean): Single<ArtistListLoaded> {
        if (isLoadMore) {
            ++currentPage
        } else {
            isReachBottom = false
            currentPage = 1
            artistList.clear()
        }
        return repository.searchArtist(query, currentPage).map {
            isReachBottom =
                (it.results.startIndex + (currentPage * it.results.itemsPerPage.toInt())) >= it.results.totalResults
            artistList.addAll(it.results.artists.artist)

            ArtistListLoaded(artistList, isReachBottom)
        }
    }

}