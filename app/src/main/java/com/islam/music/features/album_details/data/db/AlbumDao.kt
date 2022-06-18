package com.islam.music.features.album_details.data.db

import androidx.room.*
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import io.reactivex.rxjava3.core.Single


@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavoriteList(album: AlbumEntity): Long

    @Query("DELETE FROM album WHERE artistName = :artistName and albumName = :albumName")
    suspend fun removeFromFavoriteList(albumName: String?, artistName: String?)

    @Query("SELECT * FROM album")
    fun getFavoriteList(): Single<List<AlbumEntity>>

    @Query("SELECT * FROM album WHERE artistName = :artistName and albumName = :albumName")
    fun getOneFavoriteAlbum(artistName: String, albumName: String): AlbumEntity

}