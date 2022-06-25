package com.islam.music.features.search.presentation.viewmodel

import com.islam.music.common.Action

sealed class SearchActions : Action{
    data class SearchArtistByName(val query: String, val isLoadMore: Boolean = false) : SearchActions()
}
