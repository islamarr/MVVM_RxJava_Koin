package com.islam.music.features.search.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.islam.music.common.data.DataResponse
import com.islam.music.common.view.BaseViewModel
import com.islam.music.features.search.domain.entites.ArtistListLoaded
import com.islam.music.features.search.domain.usecases.SearchArtistUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchViewModel(private val useCase: SearchArtistUseCase) :
    BaseViewModel() {

    private val response = MutableLiveData<DataResponse<ArtistListLoaded>>()
    val state: LiveData<DataResponse<ArtistListLoaded>>
        get() = response

    fun fetchArtists(query: String, isLoadMore: Boolean) {
        response.postValue(DataResponse.Loading)
        compositeDisposable.add(
            useCase.execute(query, isLoadMore)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userList ->
                    response.postValue(DataResponse.Success(userList))
                }, { throwable ->
                    response.postValue(DataResponse.Failure(throwable.message))
                })
        )
    }

}