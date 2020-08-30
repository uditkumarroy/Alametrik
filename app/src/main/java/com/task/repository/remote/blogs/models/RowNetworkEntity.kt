package com.task.repository.remote.blogs.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RowNetworkEntity(
    @SerializedName("trackId")
    @Expose
    val trackId: Int,
    @SerializedName("artistName")
    @Expose
    val artistName: String,
    @SerializedName("trackName")
    @Expose
    val trackName: String,
    @SerializedName("artworkUrl60")
    @Expose
    val previewUrl: String,
    @SerializedName("trackCensoredName")
    @Expose
    val trackCensoredName: String,
    @SerializedName("trackPrice")
    @Expose
    val trackPrice: Double,
    @SerializedName("releaseDate")
    @Expose
    val releaseDate: String
)