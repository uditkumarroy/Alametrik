package com.task.di

import android.content.Context
import com.task.repository.BlogRepository
import com.task.repository.local.blogs.CachedMapper
import com.task.repository.local.blogs.room.TracksDao
import com.task.repository.remote.NetworkMapper
import com.task.repository.remote.blogs.BlogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideBlogRepository(
        tracksDao: TracksDao,
        blogApi: BlogApi,
        cachedMapper: CachedMapper,
        networkMapper: NetworkMapper,
        @ApplicationContext appContext: Context
    ): BlogRepository {
        return BlogRepository(tracksDao, blogApi, cachedMapper, networkMapper, appContext)
    }
}