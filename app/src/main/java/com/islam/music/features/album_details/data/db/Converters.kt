package com.islam.music.features.album_details.data.db

import androidx.room.TypeConverter
import com.islam.music.features.album_details.domain.entites.Track
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.util.*

class Converters {

    companion object {

        @TypeConverter
        @JvmStatic
        fun fromStringToList(data: String): List<Track> {
            val moshi = Moshi.Builder().build()
            val membersType = Types.newParameterizedType(List::class.java, Track::class.java)
            val membersAdapter = moshi.adapter<List<Track>>(membersType)
            return membersAdapter.fromJson(data).orEmpty()
        }

        @TypeConverter
        @JvmStatic
        fun fromListToString(trackList: List<Track>): String {
            val moshi = Moshi.Builder().build()
            val membersType = Types.newParameterizedType(List::class.java, Track::class.java)
            val membersAdapter = moshi.adapter<List<Track>>(membersType)
            return membersAdapter.toJson(trackList)
        }


    }
}