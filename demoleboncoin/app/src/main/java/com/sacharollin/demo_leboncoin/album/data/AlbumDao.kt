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

    @Query("SELECT * FROM tracks WHERE albumId = :albumId ORDER BY title")
    fun getTracksFromAlbumId(albumId: Int): Flow<List<Track>>

    @Query("SELECT tracks.albumId AS id, tracks.thumbnailUrl AS thumbnailUrl, tracks.title AS title FROM tracks GROUP BY tracks.albumId")
    fun getAlbums(): Flow<List<Album>>

    @Insert(onConflict = REPLACE)
    suspend fun insertTracks(tracks: List<Track>)
}