package com.example.vinilosdjam

import androidx.appcompat.widget.ActionBarOverlayLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import com.example.vinilosdjam.adapters.AlbumListsAdapter
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.ext.junit.rules.activityScenarioRule
import kotlinx.coroutines.delay
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.RecursiveAction


@RunWith(AndroidJUnit4::class)
class AlbumListActivityTest {

    @Test
    fun test_activity_in_view(){
        val activityScenario = ActivityScenario.launch(AlbumListActivity::class.java)
        Espresso.onView(withId(R.id.album_list_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_items_are_visibles() {
        val activityScenario = ActivityScenario.launch(AlbumListActivity::class.java)
        Espresso.onView(withId(R.id.rvAlbumList)).check(matches(isDisplayed()))
    }

    @Test
    fun test_nav_album_detail() {
        val activityScenario = ActivityScenario.launch(AlbumListActivity::class.java)
        Espresso.onView(withId(R.id.rvAlbumList)).check(matches(isDisplayed()))
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Espresso.onView(withId(R.id.rvAlbumList)).perform(RecyclerViewActions.actionOnItemAtPosition<AlbumListsAdapter.ViewHolder>(1, click()))
        Espresso.onView(withId(R.id.album_list_detail_view)).check((matches(isDisplayed())))
        Espresso.pressBack()
        Espresso.onView(withId(R.id.album_list_view)).check((matches(isDisplayed())))
    }





}
