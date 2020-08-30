package com.task

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.task.repository.local.blogs.room.RowCachedEntity
import com.task.ui.BlogFragment
import com.task.ui.BlogListAdapter
import com.task.ui.MainActivity
import com.task.ui.MainFragmentFactory
import com.task.util.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltAndroidTest
class MainTest {

   /* @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    val ROW_1: RowCachedEntity = RowCachedEntity(
        "Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony",
        "\"http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg\"",
        "Beavers"
    )
    val LIST_COUNT = 13
    val BLOGS_IN_TEST = listOf(ROW_1)


    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun test_sample() {
        hiltRule.inject()
    }

    @Test
    fun mainActivityLaunchTest() {
        //Activity launch test
        onView(withId(R.id.main_lay)).check(matches(isDisplayed()))
    }

    @Test
    fun test_main_fragment() {
        //Fragment test launch
        val scenario = launchFragmentInHiltContainer<BlogFragment>(
            factory = fragmentFactory
        )
        onView(withId(R.id.blogs_main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_recycler_fragment() {
        //Check recycler view available
        val scenario = launchFragmentInHiltContainer<BlogFragment>(
            factory = fragmentFactory
        )
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun item_List_display() {
        val scenario = launchFragmentInHiltContainer<BlogFragment>(
            factory = fragmentFactory
        )
        onView(withId(R.id.recyclerView)).check(matches(isCompletelyDisplayed()))
        //onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition<BlogListAdapter.BlogViewHolder>(0, matches()));
        // onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition<BlogViewHolder>(0, click()))
        //onView(withId(R.id.tv_title)).check(matches(withText(BLOGS_IN_TEST.get(0).title)))
    }
*/

}