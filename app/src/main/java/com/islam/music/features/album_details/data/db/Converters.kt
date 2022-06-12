package com.islam.music.features.album_details.data.db

import androidx.room.TypeConverter
import com.islam.music.features.album_details.domain.entites.Track
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.util.*

class Converters {

    companion object {

        @TypeConverter
        @JvmStatic
        fun fromStringToList(data: String): List<Track> {
            val moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<List<Track>> = moshi.adapter<List<Track>>(
                Track::class.java
            )
            return jsonAdapter.fromJson(data) ?: listOf()
        }

        @TypeConverter
        @JvmStatic
        fun fromListToString(trackList: List<Track>): String {
            val moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<List<Track>> = moshi.adapter<List<Track>>(
                Track::class.java
            )
            return jsonAdapter.toJson(trackList)
        }


    }
}