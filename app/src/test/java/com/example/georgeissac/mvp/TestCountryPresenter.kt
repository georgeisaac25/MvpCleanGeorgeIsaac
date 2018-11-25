package com.example.georgeissac.mvp

import com.example.georgeissac.mvp.datasource.RemoteDataSourceInterface
import com.example.domain.domain.CountryPojo
import com.example.domain.domain.countryUseCase.GetCountryUseCase
import com.example.georgeissac.mvp.domain.countryUseCase.interfaces.RepositoryInterface
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
import org.junit.Before
import org.junit.Test
import com.example.georgeissac.mvp.domain.countryUseCase.interfaces.UseCaseInterface


class TestCountryPresenter{

    private val testScheduler = TestScheduler()
    private val repository: RepositoryContract = mock()
    private val searchCountryUseCase: SearchCountryUseCase = SearchCountryUseCase(repository)
    private val getCountryUseCase: GetCountryUseCase = GetCountryUseCase(repository)
    private val view: CountryContract.view = mock()
    private lateinit var testSubject: CountryPresenter
    var list = ArrayList<CountryPojo>();
    var stringSearch = "India"
    var repositoryContract : RepositoryContract = mock()
    var remoteDataSourceInterface : RemoteDataSourceInterface = mock()

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler({ _ -> testScheduler })
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
        testSubject = CountryPresenter(view,searchCountryUseCase,getCountryUseCase)

        list.add(CountryPojo("india","ind","in","jjdd"))

    }

    @Test
    fun searchCountry_givenSuccess_calls_ShowList() {

        whenever(repository.searchCountryInDb(any())).thenReturn(Maybe.just(list))

        testSubject.searchCountry(stringSearch)
        testScheduler.triggerActions()

        // THEN
        verify(view).showList(list)
    }

    @Test
    fun searchCountry_givenSuccess_Doesnt_call_ShowList_when_view_null() {

        whenever(repository.searchCountryInDb(any())).thenReturn(Maybe.just(list))

        testSubject.onStop()
        testSubject.searchCountry(stringSearch)
        testScheduler.triggerActions()
        // THEN
        verify(view, never()).showList(list)
    }


    @Test
    fun searchCountry_givenSuccess_calls_NoList() {


        val emptySource = Maybe.empty<List<CountryPojo>>()

        whenever(repository.searchCountryInDb(any())).thenReturn(emptySource)

        testSubject.searchCountry(stringSearch)
        testScheduler.triggerActions()

        // THEN
        verify(view, never()).showList(emptyList())
    }


    @Test
    fun searchCountry_givenError_calls_Throwable() {
        // GIVEN
        val throwable = RuntimeException()
        whenever(repository.searchCountryInDb(any())).thenReturn(Maybe.error(throwable))

        // WHEN
        testSubject.searchCountry(stringSearch)
        testScheduler.triggerActions()

        // THEN
        verify(view).showError(Constants.tryAgain)
    }


    /***********/

    @Test
    fun getCountyList_givenSuccess_calls_ShowList() {

    }


}