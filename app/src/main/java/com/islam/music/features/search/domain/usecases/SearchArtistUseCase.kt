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

    /*suspend fun execute(query: String, isLoadMore: Boolean): SearchStates {
        if (isLoadMore) {
            ++currentPage
        } else {
            isReachBottom = false
            currentPage = 1
            artistList.clear()
        }
        return when (val response = repository.searchArtist(query, currentPage)) {
            is DataResponse.Success -> {
                response.data?.let {
                    isReachBottom =
                        (it.results.startIndex + (currentPage * it.results.itemsPerPage.toInt())) >= it.results.totalResults
                    artistList.addAll(it.results.artists.artist)

                    if (artistList.isEmpty()) SearchStates.EmptyArtistList else
                        SearchStates.ArtistListLoaded(artistList, isReachBottom)
                } ?: SearchStates.ShowErrorMessage()
            }
            is DataResponse.Failure -> {
                response.reason?.let {
                    SearchStates.ShowErrorMessage(response.reason)
                } ?: SearchStates.ShowErrorMessage()
            }
        }
    }*/

    fun execute(query: String, isLoadMore: Boolean): Single<ArtistListLoaded> {
        if (isLoadMore) {
            ++currentPage
        } else {
            isReachBottom = false
            currentPage = 1
            artistList.clear()
        }
        return repository.searchArtist(query, currentPage).map {
            it.let {
                isReachBottom =
                    (it.results.startIndex + (currentPage * it.results.itemsPerPage.toInt())) >= it.results.totalResults
                artistList.addAll(it.results.artists.artist)

                ArtistListLoaded(artistList, isReachBottom)
            }

        }

        /* fun execute(query: String, isLoadMore: Boolean): Observable<SearchStates> {
             //  return Observable.just(SearchStates.EmptyArtistList)
             if (isLoadMore) {
                 ++currentPage
             } else {
                 isReachBottom = false
                 currentPage = 1
                 artistList.clear()
             }
             return repository.searchArtist(query, currentPage)
                 .subscribeOn(Schedulers.io())
                 .map { dataResponse ->
                 when (dataResponse) {
                     is DataResponse.Success -> {
                         dataResponse.data?.let {
                             isReachBottom =
                                 (it.results.startIndex + (currentPage * it.results.itemsPerPage.toInt())) >= it.results.totalResults
                             artistList.addAll(it.results.artists.artist)

                             if (artistList.isEmpty()) SearchStates.EmptyArtistList else

                                 SearchStates.ArtistListLoaded(
                                     artistList,
                                     isReachBottom
                                 )

                         } ?: SearchStates.ShowErrorMessage()
                     }
                     is DataResponse.Failure -> {
                         dataResponse.reason?.let {
                             SearchStates.ShowErrorMessage(dataResponse.reason)
                         } ?: SearchStates.ShowErrorMessage()
                     }
                 }
             }.toObservable()*/
    }

}