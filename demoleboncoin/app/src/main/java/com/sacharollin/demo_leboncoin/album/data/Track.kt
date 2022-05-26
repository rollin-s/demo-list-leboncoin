package com.sacharollin.demo_leboncoin.album.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Data class representing a music album
 */
@Entity(tableName = "tracks")
data class Track(
    @field:SerializedName("id")
    @PrimaryKey
    val id: Int,

    @field:SerializedName("albumId") val albumId: Int,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("url") val url: String,
    @field:SerializedName("thumbnailUrl") val thumbnailUrl: String,
)