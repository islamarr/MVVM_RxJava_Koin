package com.islam.music.features.main_screen.domain.usecases

import com.islam.music.features.top_albums.domain.entites.Album

data class SavedListLoaded(val topAlbumsList: List<Album>)