package com.example.vinilosdjam

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.runner.AndroidJUnit4
import com.example.vinilosdjam.adapters.AlbumListsAdapter
import com.example.vinilosdjam.adapters.ArtistListAdapter
import com.google.android.material.tabs.TabLayout

import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CompletableFuture.allOf

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
    }

    @Test
    fun test_nav_album_detail() {
        val activityScenario = ActivityScenario.launch(TabsActivity::class.java)
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Espresso.onView(withId(R.id.rvFragmentAlbumList)).check(matches(isDisplayed()))
        try {
            Thread.sleep(8000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Espresso.onView(withId(R.id.viewPager)).perform(swipeLeft());
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Espresso.onView(withId(R.id.rvFragmentArtistList)).perform(RecyclerViewActions.actionOnItemAtPosition<ArtistListAdapter.ViewHolder>(0, click()))
        Espresso.pressBack()
        Espresso.onView(withId(R.id.rvFragmentArtistList)).check((matches(isDisplayed())))

    }


}
