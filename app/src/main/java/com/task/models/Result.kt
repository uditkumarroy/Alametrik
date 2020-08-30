package com.task.models

data class Result(
    val trackId: Int,
    val artistName: String,
    val trackName: String,
    val previewUrl: String,
    val trackCensoredName: String,
    val trackPrice: Double,
    val releaseDate: String
)