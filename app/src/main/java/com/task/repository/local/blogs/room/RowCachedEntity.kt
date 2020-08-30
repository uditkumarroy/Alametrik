package com.task.repository.local.blogs.room

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks")
class RowCachedEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "trackId")
    var id: Int,
    @ColumnInfo(name = "artistName")
    @Nullable
    var artistName: String,
    @ColumnInfo(name = "trackName")
    @Nullable
    var trackName: String,
    @ColumnInfo(name = "previewUrl")
    @Nullable
    var previewUrl: String,
    @ColumnInfo(name = "trackCensoredName")
    @Nullable
    var trackCensoredName: String,
    @ColumnInfo(name = "trackPrice")
    @Nullable
    var trackPrice: Double,
    @ColumnInfo(name = "releaseDate")
    @Nullable
    var releaseDate: String

)