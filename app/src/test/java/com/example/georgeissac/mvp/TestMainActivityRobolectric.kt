package com.example.georgeissac.mvp

import android.os.Build
import android.os.Bundle
import android.provider.SyncStateContract
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.example.georgeissac.mvp.domain.CountryPojo
import com.example.georgeissac.mvp.userinterface.activity.CountryActivity
import com.example.georgeissac.mvp.userinterface.activity.ShowCountryDetailActivity
import com.example.georgeissac.mvp.util.Constants
import com.google.common.base.Predicates.equalTo
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import junit.framework.TestCase.assertNotNull
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowActivity
import org.robolectric.shadows.ShadowIntent


import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Assert.*


import org.hamcrest.CoreMatchers.equalTo
import org.robolectric.shadows.ShadowToast


@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(Build.VERSION_CODES.M))
class TestMainActivityRobolectric {

    lateinit var countryActivity: CountryActivity
    lateinit var recyclerView : RecyclerView
    var list = ArrayList<CountryPojo>();



    var bundle: Bundle = mock()

    @Before
    fun init() {
        countryActivity = Robolectric.setupActivity(CountryActivity::class.java)
        recyclerView = countryActivity.findViewById(R.id.recyclerView) as RecyclerView
        list.add(CountryPojo("india","ind","in","jjdd"))
    }

    @Test
    fun testToolBar_ifExist() {
        val toolbar = countryActivity.findViewById<Toolbar>(R.id.toolbar)
        assertNotNull(toolbar);
    }

    @Test
    fun navigateToShowCountryDetailActivity_Test() {
        countryActivity.navigateToShowCountryDetailActivity(any())
        val showActivity: ShadowActivity = Shadows.shadowOf(countryActivity)
        val intent = showActivity.nextStartedActivity
        val shadowIntent = shadowOf(intent)
        assertThat(
            shadowIntent.intentClass.name,
            org.hamcrest.CoreMatchers.equalTo(ShowCountryDetailActivity::class.java!!.getName())
        )
    }

    @Test
    fun showList_Test (){
        countryActivity.showList(listOf())
        assertNotNull(recyclerView)
        assertTrue(recyclerView.isShown)
    }

    @Test
    fun showError_Test (){
        countryActivity.showError(Constants.noList)
        Assert.assertEquals(Constants.noList, ShadowToast.getTextOfLatestToast());
    }


}