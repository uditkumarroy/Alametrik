package com.task.repository.remote.blogs.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TrackNetworkEntity(
    @SerializedName("resultCount")
    @Expose
    var resultCount: String,

    @SerializedName("results")
    @Expose
    var rows: List<RowNetworkEntity>
)