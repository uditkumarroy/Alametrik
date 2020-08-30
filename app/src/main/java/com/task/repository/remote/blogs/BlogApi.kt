package com.task.repository.remote.blogs

import com.task.repository.remote.blogs.models.TrackNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogApi {

    @GET("?term=all")
    suspend fun getBlogs(): TrackNetworkEntity
}