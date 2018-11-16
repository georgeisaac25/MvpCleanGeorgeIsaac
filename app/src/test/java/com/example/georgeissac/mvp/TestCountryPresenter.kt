package com.example.georgeissac.mvp

import com.example.georgeissac.mvp.data.RemoteDataSourceInterface
import com.example.georgeissac.mvp.domain.CountryPojo
import com.example.georgeissac.mvp.domain.countryUseCase.GetCountryUseCase
import com.example.georgeissac.mvp.domain.countryUseCase.interfaces.RepositoryInterface
import com.example.georgeissac.mvp.domain.interfaces.RepositoryContract
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
import org.mockito.Mockito.doAnswer



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

    var useCaseInterface :UseCaseInterface = mock()

    var repositoryInterface : RepositoryInterface = mock()


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

        whenever(repository.searchCountryInDb(any())).thenReturn(Maybe.just(emptyList()))

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

        //whenever(getCountryUseCase.setResultWhenSucess(list).the

        doAnswer {i -> (i.arguments[0]  as UseCaseInterface).setResultWhenSucess(listOf())
        }.whenever(repositoryContract).getCountries(any())

        testSubject.getCountyList()

        // THEN
        //verify(view).showList(list)

        /*doAnswer(object : Answer {
            @Throws(Throwable::class)
            override fun answer(invocation: InvocationOnMock): Any? {
                (invocation.arguments[0] as UseCaseInterface).setResultWhenSucess(list)
                return null
            }
        }).whenever(remoteDataSourceInterface).setResultWhenSuccess(any())*/


        verify(remoteDataSourceInterface).setResultWhenSuccess(listOf())
    }


}