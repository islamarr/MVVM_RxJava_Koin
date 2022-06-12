package com.islam.music.features.main_screen.presentation.viewmodel

import com.islam.music.common.Action

sealed class MainScreenActions : Action{
    object LoadAllSavedAlbums : MainScreenActions()
}
