package com.islam.music.features.album_details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.islam.music.common.data.DataResponse
import com.islam.music.common.view.BaseViewModel
import com.islam.music.features.album_details.domain.entites.AlbumDetailsData
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.album_details.domain.entites.SavedState
import com.islam.music.features.album_details.domain.usecases.AlbumDetailsUseCase
import com.islam.music.features.album_details.domain.usecases.GetFavoriteUseCase
import com.islam.music.features.album_details.domain.usecases.SetFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val albumDetailsUseCase: AlbumDetailsUseCase,
    private val getFavoriteUseCase: GetFavoriteUseCase,
    private val setFavoriteUseCase: SetFavoriteUseCase
) :
    BaseViewModel() {

    private val response = MutableLiveData<DataResponse<AlbumDetailsData>>()
    val state: LiveData<DataResponse<AlbumDetailsData>>
        get() = response

    private val response2 = MutableLiveData<SavedState>()
    val state2: LiveData<SavedState>
        get() = response2


    fun fetch(albumParams: AlbumParams) {
        response.postValue(DataResponse.Loading)
        val d1: Disposable = albumDetailsUseCase.execute(albumParams)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                response.postValue(DataResponse.Success(it))
            }, { throwable ->
                response.postValue(DataResponse.Failure(throwable.message))
            })
        val d2: Disposable =
            getFavoriteUseCase.execute(albumParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    response2.postValue(SavedState(true))
                }, {
                    response2.postValue(SavedState(false))
                })

        compositeDisposable.add(d1)
        compositeDisposable.add(d2)

    }

    fun setFavorite(isAdd: Boolean, albumEntity: AlbumEntity) {
        Completable.fromAction { //TODO make enhancement here
            setFavoriteUseCase.execute(isAdd, albumEntity)
        }.subscribeOn(Schedulers.io()).subscribe()
    }

}