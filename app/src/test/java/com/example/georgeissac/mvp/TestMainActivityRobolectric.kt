package com.example.georgeissac.mvp

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.bumptech.glide.util.Util
import com.example.domain.domain.CountryPojo
import com.example.georgeissac.mvp.presentation.interfaces.CountryContract
import com.example.georgeissac.mvp.userinterface.activity.CountryActivity
import com.example.georgeissac.mvp.userinterface.activity.ShowCountryDetailActivity
import com.example.georgeissac.mvp.util.Constants
import com.example.georgeissac.mvp.util.Utilities
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.Shadows.shadowOf
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowActivity
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Assert.*
import org.robolectric.shadows.ShadowToast

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(Build.VERSION_CODES.M))
class TestMainActivityRobolectric {

    private lateinit var countryActivity: CountryActivity
    private lateinit var recyclerView: RecyclerView
    private var list = ArrayList<CountryPojo>();
    private lateinit var toolbar: Toolbar;
    private val context: Context = mock()
    private val stringAppName: String = "Mvp"

    @Before
    fun init() {
        countryActivity = Robolectric.setupActivity(CountryActivity::class.java)
        recyclerView = countryActivity.findViewById(R.id.recyclerView) as RecyclerView
        toolbar = countryActivity.findViewById<Toolbar>(R.id.toolbar)
        list.add(CountryPojo("india", "ind", "in", "jjdd"))
    }

    @Test
    fun testToolBar_ifExist() {
        assertNotNull(toolbar);
    }

    @Test
    fun testToolBar_NameEqualToAppName() {
        whenever(context.getString(R.string.app_name)).thenReturn(stringAppName)
        junit.framework.Assert.assertEquals(toolbar.title, context.getString(R.string.app_name));
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
    fun showList_Test() {
        countryActivity.showList(listOf())
        assertNotNull(recyclerView)
        assertTrue(recyclerView.isShown)
    }

    @Test
    fun showError_Test() {
        countryActivity.showError(Constants.noList)
        assertFalse(recyclerView.isShown)
        Assert.assertEquals(Constants.noList, ShadowToast.getTextOfLatestToast());
    }
}