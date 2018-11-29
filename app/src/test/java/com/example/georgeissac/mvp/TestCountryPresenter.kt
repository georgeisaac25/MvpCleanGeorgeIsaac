package com.example.georgeissac.mvp

import com.example.data.datasource.RemoteDataSourceInterface
import com.example.domain.domain.CountryPojo
import com.example.domain.domain.countryUseCase.GetCountryUseCase
import com.example.domain.domain.interfaces.RepositoryContract
import com.example.georgeissac.mvp.domain.searchCountryUseCase.SearchCountryUseCase
import com.example.georgeissac.mvp.presentation.interfaces.CountryContract
import com.example.georgeissac.mvp.presentation.presenter.CountryPresenter
import com.example.georgeissac.mvp.util.Constants
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Maybe
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.hamcrest.Matchers.`is` as is_



class TestCountryPresenter {

    private val testScheduler = TestScheduler()
    private val repository: RepositoryContract = mock()
    private val searchCountryUseCase: SearchCountryUseCase = SearchCountryUseCase(repository)
    private val getCountryUseCase: GetCountryUseCase = GetCountryUseCase(repository)
    private val view: CountryContract.view = mock()
    private lateinit var testSubject: CountryPresenter
    var list = ArrayList<CountryPojo>();
    var stringSearch = "India"

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler({ _ -> testScheduler })
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
        testSubject = CountryPresenter(view, searchCountryUseCase, getCountryUseCase)
        list.add(CountryPojo("india", "ind", "in", "jjdd"))
    }

    @Test
    fun searchCountry_givenSuccess_calls_ShowList() {
        whenever(repository.searchCountryInDb(any())).thenReturn(Maybe.just(list))
        testSubject.searchCountry(stringSearch)
        testScheduler.triggerActions()
        verify(view).showList(list)
    }

    @Test
    fun searchCountry_givenSuccess_Doesnt_call_ShowList_when_view_null() {
        whenever(repository.searchCountryInDb(any())).thenReturn(Maybe.just(list))
        testSubject.onStop()
        testSubject.searchCountry(stringSearch)
        testScheduler.triggerActions()
        verify(view, never()).showList(list)
    }

    @Test
    fun searchCountry_givenSuccess_calls_NoList() {
        val emptySource = Maybe.empty<List<CountryPojo>>()
        whenever(repository.searchCountryInDb(any())).thenReturn(emptySource)
        testSubject.searchCountry(stringSearch)
        testScheduler.triggerActions()
        verify(view, never()).showList(emptyList())
    }


    @Test
    fun searchCountry_givenError_calls_Throwable() {
        val throwable = RuntimeException()
        whenever(repository.searchCountryInDb(any())).thenReturn(Maybe.error(throwable))
        testSubject.searchCountry(stringSearch)
        testScheduler.triggerActions()
        verify(view).showError(Constants.tryAgain)
    }

    @Test
    fun getCountyListUsingRx_givenSuccess_calls_ShowList() {
        whenever(repository.getCountryRx()).thenReturn(Maybe.just(list))
        testSubject.getCountyListUsingRx()
        testScheduler.triggerActions()
        verify(view).showList(list)
    }

    @Test
    fun getCountyListUsingRx_givenSuccess_calls_NoList() {
        val emptySource = Maybe.empty<List<CountryPojo>>()
        whenever(repository.getCountryRx()).thenReturn(emptySource)
        testSubject.getCountyListUsingRx()
        testScheduler.triggerActions()
        verify(view, never()).showList(emptyList())
    }

    @Test
    fun getCountyListUsingRx_givenSuccess_calls_Throwablr() {
        whenever(repository.getCountryRx()).thenReturn(Maybe.error(RuntimeException()))
        testSubject.getCountyListUsingRx()
        testScheduler.triggerActions()
        verify(view).showError(Constants.tryAgain)
    }

    @Test
    fun getCountyListUsingRx_Doesnt_call_ShowList_when_view_null() {
        //BDD
        given(repository.getCountryRx()).willReturn(Maybe.just(list))
        testSubject.onStop()

        //when
        testSubject.getCountyListUsingRx()
        testScheduler.triggerActions()

        //then
        verify(view, never()).showList(list)
        //assertThat(list.size,is_(2))
    }


}