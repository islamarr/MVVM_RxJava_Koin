package com.islam.music.features.album_details.data.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.Track
import com.islam.music.features.search.domain.entites.Artist
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class AlbumDaoTest : TestCase() {

    private lateinit var albumDao: AlbumDao
    private lateinit var db: AlbumDatabase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AlbumDatabase::class.java).build()
        albumDao = db.getAlbumDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertNewAlbum_ReadInList() = runBlocking {
        val albumEntity = AlbumEntity(
            "artistName", "https", "albumName",
            listOf(Track(Artist(name = "name"), duration = 1000, name = "track"))
        )
        albumDao.addToFavoriteList(albumEntity)
        val albumList = albumDao.getFavoriteList()
        assertThat(albumList[0], CoreMatchers.equalTo(albumEntity))
    }

    @Test
    @Throws(Exception::class)
    fun insertNewAlbum_GetOneAlbum() = runBlocking {
        val albumEntity = AlbumEntity(
            "artistName", "https", "albumName",
            listOf(Track(Artist(name = "name"), duration = 1000, name = "track"))
        )
        albumDao.addToFavoriteList(albumEntity)
        val album = albumDao.getOneFavoriteAlbum(albumEntity.artistName!!, albumEntity.albumName!!)
        assertThat(album, CoreMatchers.equalTo(albumEntity))
    }

    @Test
    @Throws(Exception::class)
    fun insertAndDeleteAlbum_ReturnEmptyList() = runBlocking {
        val albumEntity = AlbumEntity(
            "artistName", "https", "albumName",
            listOf(Track(Artist(name = "name"), duration = 1000, name = "track"))
        )
        albumDao.addToFavoriteList(albumEntity)
        albumDao.removeFromFavoriteList(albumEntity.albumName, albumEntity.artistName)
        val albumList = albumDao.getFavoriteList()
        assertThat(albumList.size, CoreMatchers.equalTo(0))
    }

}
