package com.example.georgeissac.mvp.userinterface.activity


import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.georgeissac.mvp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.v7.widget.SearchView
import com.example.georgeissac.mvp.presentation.presenter.CountryPresenter
import com.nhaarman.mockito_kotlin.mock
import junit.framework.Assert.assertNotSame
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import android.support.test.espresso.matcher.ViewMatchers.withParent
import android.view.View
import android.view.View.VISIBLE
import android.widget.TextView
import android.view.KeyEvent.KEYCODE_ENTER
import android.support.test.espresso.action.ViewActions.typeText
import android.widget.EditText
import android.support.test.espresso.Espresso.onView
import android.view.KeyEvent
import junit.framework.Assert.assertSame


@LargeTest
@RunWith(AndroidJUnit4::class)
class CountryActivityTest {

    //val presenter : CountryPresenter = mock()

    val stringTestSuccess = "india"
    val stringTestError = ""


    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(CountryActivity::class.java)

    @Test
    fun showList_Test() {

        Thread.sleep(5000)

        onView(withId(R.id.action_searcher)).check(android.support.test.espresso.assertion.ViewAssertions.matches(
            isDisplayed()))

        onView(withId(R.id.action_searcher)).perform(click());

        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText(stringTestError),
            pressKey(KeyEvent.KEYCODE_ENTER)
        )
        val recyclerView : RecyclerView = mActivityTestRule.activity.findViewById(R.id.recyclerView)

        assertNotSame(0, recyclerView.adapter.itemCount)

        onView(withId(R.id.recyclerView)).check(android.support.test.espresso.assertion.ViewAssertions.matches(
            isDisplayed()))

        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText(stringTestSuccess),
            pressKey(KeyEvent.KEYCODE_ENTER)
        )

        assertNotSame(0, recyclerView.adapter.itemCount)

        onView(withId(R.id.recyclerView)).check(android.support.test.espresso.assertion.ViewAssertions.matches(
            isDisplayed()))
    }
}
