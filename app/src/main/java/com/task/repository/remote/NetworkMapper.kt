package com.task.repository.remote

import android.util.Log
import com.task.models.Result
import com.task.repository.remote.blogs.models.RowNetworkEntity
import com.task.utils.EntityMapping
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() :
    EntityMapping<RowNetworkEntity, Result> {

    override fun mapFromEntity(entity: RowNetworkEntity): Result {
        Log.e("Row", " : ${entity.artistName}")
        return Result(
            trackId = entity.trackId,
            artistName = "${entity.artistName}",
            trackName = "${entity.trackName}",
            trackCensoredName = "${entity.trackCensoredName}",
            previewUrl = "${entity.previewUrl}",
            trackPrice = entity.trackPrice,
            releaseDate = "${entity.releaseDate}"
        )
    }

    override fun mapToEntity(domainModel: Result): RowNetworkEntity {
        return RowNetworkEntity(
            trackId = domainModel.trackId,
            artistName = domainModel.artistName,
            trackName = domainModel.trackName,
            trackCensoredName = domainModel.trackCensoredName,
            previewUrl = domainModel.previewUrl,
            trackPrice = domainModel.trackPrice,
            releaseDate = domainModel.releaseDate
        )
    }


    fun mapFromEntityList(entities: List<RowNetworkEntity>): List<Result> {
        return entities.map { mapFromEntity(it) }
    }

}