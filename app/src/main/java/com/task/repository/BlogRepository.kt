package com.task.repository

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.google.gson.Gson
import com.task.models.Result
import com.task.repository.local.blogs.CachedMapper
import com.task.repository.local.blogs.room.RowCachedEntity
import com.task.repository.local.blogs.room.TracksDao
import com.task.repository.remote.NetworkMapper
import com.task.repository.remote.blogs.BlogApi
import com.task.repository.remote.blogs.models.TrackNetworkEntity
import com.task.utils.DataState
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.io.InputStream


class BlogRepository
constructor(
    private val tracksDao: TracksDao,
    private val blogApi: BlogApi,
    private val cachedMapper: CachedMapper,
    private val networkMapper: NetworkMapper,
    @ApplicationContext val appContext: Context
) {

    suspend fun getBlog(): Flow<DataState<List<Result>>> = flow {
        emit(DataState.Loading)
        try {
            //Retrieve data from the api

            var json: String? = null
            json = try {
                val inputStream: InputStream = (appContext.assets as AssetManager).open("data.json")
                inputStream.bufferedReader().use{it.readText()}
            } catch (ex: IOException) {
                ex.printStackTrace().toString()
            }
            val gson = Gson()
            val trackData: TrackNetworkEntity = gson.fromJson(json, TrackNetworkEntity::class.java)
            Log.e("Json","${json}")
            //val networBlogs = blogApi.getBlogs().rows
            //map to entity
            val blogs = networkMapper.mapFromEntityList(trackData.rows)
            //insert to db
            for (blog in blogs) {
                if (blog.trackId !== null)
                    tracksDao.insert(cachedMapper.mapToEntity(blog))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))

        } finally {
            //retrieve from db
            val cachedBlogs = tracksDao.getBlogs()
            emit(DataState.Sucess(cachedMapper.mapFromEntityList(cachedBlogs)))
        }

    }

    suspend fun searchTrack(search:String): Flow<DataState<List<Result>>> = flow {
        emit(DataState.Loading)
        val cachedBlogs = tracksDao.searchTracks(search)
        emit(DataState.Sucess(cachedMapper.mapFromEntityList(cachedBlogs)))

    }

    suspend fun orderBy(order:Int): Flow<DataState<List<Result>>> = flow {
        emit(DataState.Loading)
        lateinit var cachedBlogs:List<RowCachedEntity>
        when(order){
            0->{
                cachedBlogs = tracksDao.orderByDate()
            }
            1->{
                cachedBlogs = tracksDao.orderName()
            }
            2->{
                cachedBlogs = tracksDao.orderTrack()
            }
            3->{
                cachedBlogs = tracksDao.orderPrice()
            }
        }
        emit(DataState.Sucess(cachedMapper.mapFromEntityList(cachedBlogs)))

    }

}