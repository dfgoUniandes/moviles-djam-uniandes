package com.example.vinilosdjam

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Test

class albumCreateTest {

    @Test
    fun test_activity_in_view(){
        val activityScenario = ActivityScenario.launch(CreateAlbumActivity::class.java)
        Espresso.onView(withId(R.id.inputTitleAlbum))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.inputYear))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.inputCoverURL))
            .check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.inputAlbumDescription))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_create_album(){
        val activityScenario = ActivityScenario.launch(TabsActivity::class.java)
        Espresso.onView(withText("Crear álbum")).perform(click())
        Espresso.onView(withId(R.id.inputTitleAlbum))
            .perform(clearText(), typeText("album 1"))
        Espresso.onView(withId(R.id.inputYear))
            .perform(clearText(), typeText("1993"))
        Espresso.onView(withId(R.id.inputCoverURL))
            .perform(clearText(),
                typeText("https://upload.wikimedia.org"))
        Espresso.onView(withId(R.id.inputAlbumDescription))
            .perform(clearText(), typeText("descripcion de album"))
        Espresso.closeSoftKeyboard()
        Espresso.onView(withId(R.id.autoCompleteGenre)).perform(click())
        Espresso.onView(withText("Salsa")).inRoot(RootMatchers.isPlatformPopup())
            .perform(click())
        Espresso.onView(withId(R.id.autoCompleteRecordLabel)).perform(click())
        Espresso.onView(withText("EMI")).inRoot(RootMatchers.isPlatformPopup())
            .perform(click())
        Espresso.onView(withText("Agregar")).perform(click())

        Espresso.onView(withId(R.id.activity_tabs))
            .check(matches(isDisplayed()))
    }

}