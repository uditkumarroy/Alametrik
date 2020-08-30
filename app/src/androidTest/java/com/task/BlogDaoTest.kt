package com.task

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.task.repository.local.blogs.CachedMapper
import com.task.repository.local.blogs.room.RowCachedEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BlogDaoTest : BlogDatabaseTest() {

   /* val ROW_1: RowCachedEntity = RowCachedEntity("Descrption_1", "url1", "title_1")


    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val rowCachedEntity: RowCachedEntity = ROW_1
        lateinit var blogs: List<RowCachedEntity>
        runBlocking {
            getDataBase().insert(rowCachedEntity)
            blogs = getDataBase().getBlogs()
        }
        val mapRow = CachedMapper().mapFromEntity(rowCachedEntity)
        val mapRow2 = CachedMapper().mapFromEntity(blogs.get(0))
        Assert.assertEquals(mapRow, mapRow2)
    }*/


}