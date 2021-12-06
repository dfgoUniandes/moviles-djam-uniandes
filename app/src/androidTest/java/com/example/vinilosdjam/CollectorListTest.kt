package com.example.vinilosdjam

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.vinilosdjam.adapters.AlbumListsAdapter
import com.example.vinilosdjam.adapters.ArtistListAdapter
import com.example.vinilosdjam.adapters.UserListAdapter
import org.junit.Test

class CollectorListTest {

    @Test
    fun test_activity_in_view(){
        val activityScenario = ActivityScenario.launch(TabsActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.activity_tabs))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_items_are_visibles() {
        val activityScenario = ActivityScenario.launch(TabsActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.album_fragment_list_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_nav_album_detail() {
        val activityScenario = ActivityScenario.launch(TabsActivity::class.java)
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Espresso.onView(ViewMatchers.withId(R.id.rvFragmentAlbumList)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            )
        )
        try {
            Thread.sleep(8000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Espresso.onView(ViewMatchers.withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        Espresso.onView(ViewMatchers.withId(R.id.viewPager)).perform(ViewActions.swipeLeft());
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        Espresso.onView(ViewMatchers.withId(R.id.rvFragmentUserList)).check(
            ViewAssertions.matches(
                ViewMatchers.isDisplayed()
            ))
    }
}