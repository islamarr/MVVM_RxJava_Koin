package com.islam.music.common

interface OnItemClickListener {
    fun onClick(albumName: String? = null, artistName: String? = null, imgUrl: String? = null)
}