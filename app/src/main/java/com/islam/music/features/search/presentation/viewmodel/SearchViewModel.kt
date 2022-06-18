package com.islam.music.features.search.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.islam.music.common.data.DataResponse
import com.islam.music.features.search.domain.entites.ArtistListLoaded
import com.islam.music.features.search.domain.usecases.SearchArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: SearchArtistUseCase) :
    ViewModel() {

    /* override fun handle(actions: SearchActions): Flow<SearchStates> = flow {
         when (actions) {
             is SearchActions.SearchArtistByName -> {
                 emit(SearchStates.Loading)
                 emit(useCase.execute(actions.query, actions.isLoadMore))
             }
         }
     }
 */

    private val artistResponse = MutableLiveData<DataResponse<ArtistListLoaded>>()
    val state: LiveData<DataResponse<ArtistListLoaded>>  // TODO use rxjava observer
        get() = artistResponse
    private val compositeDisposable = CompositeDisposable()


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
        // TODO add .autoDisposable(this)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


    /* private val _state: Observable<SearchStates> = compose()
     val state: Observable<SearchStates>
         get() = _state

     private val actionsSubject = BehaviorSubject.create<SearchActions>()

     fun dispatch(observable: Observable<SearchActions>) {
         observable.subscribe {
             actionsSubject.onNext(it)
         }
     }

     private fun compose(): Observable<SearchStates> {
         return actionsSubject
             .compose(performActions())
             .observeOn(AndroidSchedulers.mainThread())
             .replay(VIEW_STATE_BUFFER_SIZE)
             .autoConnect(NUMBER_OF_OBSERVERS)
             .distinctUntilChanged()

     }

     private fun performActions() = ObservableTransformer<SearchActions, SearchStates> { actions ->
         Observable.merge(
             actions.ofType(SearchActions.SearchArtistByName::class.java).compose{
                 Observable.just(SearchStates.Loading)
             },
             actions.ofType(SearchActions.SearchArtistByName::class.java).compose{
                 it.flatMap { action ->
                     useCase.execute(action.query, action.isLoadMore)
                 }
             }
         )
     }*/

}