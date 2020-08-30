package com.task.di

import android.content.Context
import androidx.room.Room
import com.task.repository.local.TaskDataBase
import com.task.repository.local.blogs.room.TracksDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
object RoomPersistanceModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): TaskDataBase {
        return Room.databaseBuilder(context, TaskDataBase::class.java, TaskDataBase.DATA_BASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDao(taskDataBase: TaskDataBase): TracksDao {
        return taskDataBase.blogDao()
    }

}