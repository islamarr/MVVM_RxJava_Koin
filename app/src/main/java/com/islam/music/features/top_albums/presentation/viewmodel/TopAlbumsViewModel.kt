package com.islam.music.features.top_albums.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.islam.music.common.data.DataResponse
import com.islam.music.common.view.BaseViewModel
import com.islam.music.features.top_albums.domain.entites.TopAlbumsListLoaded
import com.islam.music.features.top_albums.domain.usecases.TopAlbumsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class TopAlbumsViewModel @Inject constructor(private val useCase: TopAlbumsUseCase) :
    BaseViewModel() {

    private val response = MutableLiveData<DataResponse<TopAlbumsListLoaded>>()
    val state: LiveData<DataResponse<TopAlbumsListLoaded>>
        get() = response

    fun fetch(artistName: String) {
        response.postValue(DataResponse.Loading)
        compositeDisposable.add(
            useCase.execute(artistName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    response.postValue(DataResponse.Success(it))
                }, { throwable ->
                    response.postValue(DataResponse.Failure(throwable.message))
                })
        )
    }

}