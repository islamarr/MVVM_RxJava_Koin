package com.islam.music.features.search.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.music.common.view.BaseViewModel
import com.islam.music.features.search.domain.usecases.SearchArtistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCase: SearchArtistUseCase) :
     BaseViewModel<SearchStates, SearchActions>(SearchStates.InitialState) {

    private val _state2 = MutableLiveData<SearchStates>(SearchStates.InitialState) //just to test livedata
    val state2: LiveData<SearchStates>
        get() = _state2

    fun dispatch2(actions: SearchActions){
        when (actions) {
            is SearchActions.SearchArtistByName -> {
                _state2.postValue(SearchStates.Loading)
                viewModelScope.launch {
                    _state2.postValue(useCase.execute(actions.query, actions.isLoadMore))
                }
            }
        }
    }

    override fun handle(actions: SearchActions): Flow<SearchStates> = flow {
        when (actions) {
            is SearchActions.SearchArtistByName -> {
                emit(SearchStates.Loading)
                emit(useCase.execute(actions.query, actions.isLoadMore))
            }
        }
    }

}