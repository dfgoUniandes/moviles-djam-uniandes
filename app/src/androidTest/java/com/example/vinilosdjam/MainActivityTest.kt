package com.example.vinilosdjam

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.runner.AndroidJUnit4

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun test_activityInView(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_components() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.textViewTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.imageViewMain)).check(matches(isDisplayed()))
        onView(withId(R.id.button4)).check(matches(isDisplayed()))
        onView(withId(R.id.button5)).check(matches(isDisplayed()))
        onView(withId(R.id.textView2)).check(matches(isDisplayed()))
    }

    @Test
    fun test_texts_is_displayed() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.textViewTitle)).check(matches(withText("Vinilos DJAM")))
        onView(withId(R.id.button4)).check(matches(withText("Usuario invitado")))
        onView(withId(R.id.button5)).check(matches(withText("Coleccionista")))
        onView(withId(R.id.textView2)).check(matches(withText("¿Registrarte como coleccionista?")))
    }

    @Test
    fun test_nav_album_list() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.button4)).perform(click())
        onView(withId(R.id.album_list_view)).check((matches(isDisplayed())))
    }
}