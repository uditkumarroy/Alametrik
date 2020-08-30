package com.task.repository.local.blogs

import com.task.models.Result
import com.task.repository.local.blogs.room.RowCachedEntity
import com.task.utils.EntityMapping
import javax.inject.Inject

class CachedMapper @Inject constructor() : EntityMapping<RowCachedEntity, Result> {

    override fun mapFromEntity(entity: RowCachedEntity): Result {
        return Result(
            trackId = entity.id,
            artistName = entity.artistName,
            trackName = entity.trackName,
            trackCensoredName = entity.trackCensoredName,
            previewUrl = entity.previewUrl,
            trackPrice = entity.trackPrice,
            releaseDate = entity.releaseDate
        )
    }

    override fun mapToEntity(domainModel: Result): RowCachedEntity {
        return RowCachedEntity(
            id = domainModel.trackId,
            artistName = domainModel.artistName,
            trackName = domainModel.trackName,
            trackCensoredName = domainModel.trackCensoredName,
            previewUrl = domainModel.previewUrl,
            trackPrice = domainModel.trackPrice,
            releaseDate = domainModel.releaseDate
        )
    }

    fun mapFromEntityList(entities: List<RowCachedEntity>): List<Result> {
        return entities.map { mapFromEntity(it) }
    }
}