package com.task

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.task.repository.local.TaskDataBase
import com.task.repository.local.blogs.room.TracksDao
import org.junit.After
import org.junit.Before

abstract class BlogDatabaseTest {

    private lateinit var taskDataBase: TaskDataBase
    fun getDataBase(): TracksDao {
        return taskDataBase.blogDao()
    }

    @Before
    fun init() {
        taskDataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskDataBase::class.java
        ).build()
    }

    @After
    fun finish() {
        taskDataBase.close()
    }
}