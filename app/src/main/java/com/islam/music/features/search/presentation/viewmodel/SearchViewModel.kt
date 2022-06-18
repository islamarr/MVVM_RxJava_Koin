package com.islam.music.features.search.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.islam.music.common.data.DataResponse
import com.islam.music.common.view.NewBaseViewModel
import com.islam.music.features.search.domain.entites.ArtistListLoaded
import com.islam.music.features.search.domain.usecases.SearchArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: SearchArtistUseCase) :
    NewBaseViewModel() {

    private val artistResponse = MutableLiveData<DataResponse<ArtistListLoaded>>()
    val state: LiveData<DataResponse<ArtistListLoaded>>  // TODO use rxjava observer
        get() = artistResponse

    fun fetchArtists(query: String, isLoadMore: Boolean) {
        //  artistResponse.postValue(DataResponse.Loading)
        compositeDisposable.add(
            useCase.execute(query, isLoadMore)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userList ->
                    artistResponse.postValue(DataResponse.Success(userList))
                }, { throwable ->
                    artistResponse.postValue(DataResponse.Failure(throwable.message))
                })
        )
    }

}