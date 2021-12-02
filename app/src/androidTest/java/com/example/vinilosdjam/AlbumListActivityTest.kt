package com.example.vinilosdjam

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import com.example.vinilosdjam.adapters.AlbumListsAdapter
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArtistListActivityTest {

    @Test
    fun test_activity_in_view(){
        val activityScenario = ActivityScenario.launch(TabsActivity::class.java)
        Espresso.onView(withId(R.id.activity_tabs))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_items_are_visibles() {
        val activityScenario = ActivityScenario.launch(TabsActivity::class.java)
        Espresso.onView(withId(R.id.album_fragment_list_view))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvFragmentAlbumList)).check(matches(isDisplayed()))
    }

    @Test
    fun test_nav_album_detail() {
        val activityScenario = ActivityScenario.launch(TabsActivity::class.java)
        Espresso.onView(withId(R.id.rvFragmentAlbumList)).check(matches(isDisplayed()))
        try {

            Thread.sleep(10000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Espresso.onView(withId(R.id.rvFragmentAlbumList)).perform(RecyclerViewActions.actionOnItemAtPosition<AlbumListsAdapter.ViewHolder>(1, click()))
        Espresso.onView(withId(R.id.album_list_detail_view)).check((matches(isDisplayed())))
        Espresso.pressBack()
        Espresso.onView(withId(R.id.rvFragmentAlbumList)).check((matches(isDisplayed())))
    }
}
