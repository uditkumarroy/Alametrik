package com.task.repository.local.blogs.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TracksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rowCachedEntity: RowCachedEntity): Long

    @Query("SELECT * from tracks")
    suspend fun getBlogs(): List<RowCachedEntity>

    @Query("SELECT * from tracks where trackName LIKE '%' || :trackName || '%'")
    suspend fun searchTracks(trackName:String): List<RowCachedEntity>

    @Query("SELECT * FROM tracks ORDER BY releaseDate DESC")
    suspend fun orderByDate(): List<RowCachedEntity>

    @Query("SELECT * FROM tracks ORDER BY artistName DESC")
    suspend fun orderName(): List<RowCachedEntity>

    @Query("SELECT * FROM tracks ORDER BY trackName DESC")
    suspend fun orderTrack(): List<RowCachedEntity>

    @Query("SELECT * FROM tracks ORDER BY trackPrice DESC")
    suspend fun orderPrice(): List<RowCachedEntity>

}