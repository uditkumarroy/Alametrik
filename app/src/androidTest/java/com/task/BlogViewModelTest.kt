package com.task

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.task.repository.BlogRepository
import com.task.repository.local.blogs.CachedMapper
import com.task.repository.local.blogs.room.TracksDao
import com.task.repository.remote.NetworkMapper
import com.task.repository.remote.blogs.BlogApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class BlogViewModelTest : BlogDatabaseTest() {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Mock
    private lateinit var handle: SavedStateHandle

    @Mock
    private lateinit var repository: BlogRepository

    @Mock
    private lateinit var tracksDao: TracksDao

    @Mock
    private lateinit var blogApi: BlogApi

    @Mock
    private lateinit var cachedMapper: CachedMapper

    @Mock
    private lateinit var networkMapper: NetworkMapper

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        tracksDao = getDataBase()
        cachedMapper = CachedMapper()
        networkMapper = NetworkMapper()
        repository = BlogRepository(tracksDao, blogApi, cachedMapper, networkMapper)
        // myViewModel = MyViewModel(handle, useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }



}