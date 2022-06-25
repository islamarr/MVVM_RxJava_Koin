package com.islam.music.common.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.music.common.Action
import com.islam.music.common.ViewState
import kotlinx.coroutines.flow.*

abstract class BaseViewModel<STATES : ViewState, ACTIONS : Action>(val initialState: STATES) :
    ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<STATES>
        get() = _state.asStateFlow()

    fun dispatch(actions: ACTIONS) {
        handle(actions).onEach { state ->
            onViewState(state)
        }.launchIn(viewModelScope)
    }

    private suspend fun onViewState(state: STATES) {
        _state.emit(state)
    }

    abstract fun handle(actions: ACTIONS): Flow<STATES>

}