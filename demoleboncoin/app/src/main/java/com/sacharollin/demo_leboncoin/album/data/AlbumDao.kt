package com.sacharollin.demo_leboncoin.album.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {
    @Query("SELECT * FROM tracks ORDER BY title")
    fun getTracks(): Flow<List<Track>>

    @Insert(onConflict = REPLACE)
    suspend fun insertTracks(tracks: List<Track>);
}