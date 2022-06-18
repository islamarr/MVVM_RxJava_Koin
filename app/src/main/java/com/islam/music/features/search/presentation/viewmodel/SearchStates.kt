package com.islam.music.features.search.presentation.viewmodel

import com.islam.music.common.ViewState
import com.islam.music.features.search.domain.entites.Artist


sealed class SearchStates : ViewState {
    object InitialState : SearchStates()
    object Loading : SearchStates()
    data class ShowErrorMessage(val reason: String? = null) :
        SearchStates()

    data class ArtistListLoaded(val result: List<Artist>, val isReachBottom: Boolean) : SearchStates()
    object EmptyArtistList : SearchStates()
}
