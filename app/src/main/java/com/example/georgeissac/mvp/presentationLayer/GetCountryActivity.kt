package com.example.georgeissac.mvp.presentationLayer

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.util.Log
import android.view.View
import com.example.georgeissac.mvp.adapter.CountryAdapter
import com.example.georgeissac.mvp.interfaces.PassPositionOfItemClicked
import com.example.georgeissac.mvp.presenter.GetPresenter
import com.example.georgeissac.mvp.view.ViewInterface
import android.support.v7.widget.*
import android.view.Menu
import com.example.georgeissac.mvp.MyApp
import com.example.georgeissac.mvp.R
import com.example.georgeissac.mvp.retrofit.ApiInterface
import com.example.georgeissac.mvp.room.MyRepository
import com.example.georgeissac.mvp.usecase.addCountry.AddCountry
import com.example.georgeissac.mvp.usecase.getCountry.response.Country
import com.example.georgeissac.mvp.usecase.getCountryOnSearch.SearchCountry
import javax.inject.Inject


class GetCountryActivity : AppCompatActivity(), PassPositionOfItemClicked, ViewInterface, LoaderManager.LoaderCallbacks<CountryPresenter> {

    private val LOADER_ID_PRESENTER = 103
    var presenter: CountryPresenter? = null


    lateinit var list: List<Country>
    lateinit var recyclerView: RecyclerView
    lateinit var countryAdapter: CountryAdapter
    private var searchView: SearchView? = null

    @Inject lateinit var
            retrofitInstance: ApiInterface

    @Inject lateinit var
            repo: MyRepository


    @Inject lateinit var
            searchCountry: SearchCountry

    @Inject lateinit var
            addCountry: AddCountry


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val appComponent = DaggerAppComponent.builder().dataBModule(DataModule(this)).useCaseModule(UseCaseModule()).build()
        appComponent.inject(this)*/

        (application as MyApp).apiComponent.inject(this)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.setLayoutManager(mLayoutManager)
        recyclerView.setItemAnimator(DefaultItemAnimator())

        val myToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(myToolbar)

        getSupportLoaderManager().initLoader(LOADER_ID_PRESENTER, null, this);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)

        searchView = menu.findItem(R.id.action_search)
                .getActionView() as SearchView

        searchView?.setSubmitButtonEnabled(true)
        searchView?.setOnQueryTextListener(onQueryTextListener)

        return super.onCreateOptionsMenu(menu)
    }


    override fun onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        presenter?.onViewAttached(this)
        presenter?.getCountyList()

    }

    override fun getPositionOfItemForSingleTapUpClick(position: Int) {
        var country = list.get(position)
    }

    override fun showList(result: MutableList<Country>) {
        if (result.size > 0) {
            this.list = result
            countryAdapter = CountryAdapter(list, this@GetCountryActivity)
            Log.e("GetCountryActivity", "insert")

            Thread(Runnable {
                /*TODO check if country already exists
                if(AppDatabase.getInstance(this)!!.countryDao().getIfExist(list.get(0).name).size==0){
                    AppDatabase.getInstance(this)!!.countryDao().insert(list)
                }*/

                presenter?.onViewAttached(this@GetCountryActivity)
                presenter?.addCountries(list,addCountry)

            }).start()
            recyclerView.setAdapter(countryAdapter)
        }
    }

    override fun showError(error: String) {
        Log.e("error", error)
    }


    override fun onLoadFinished(p0: Loader<CountryPresenter>, presenter: CountryPresenter?) {
        if (presenter != null) {
            if (presenter is CountryPresenter) {
                this.presenter = presenter

            }
        }
    }

    override fun onLoaderReset(p0: Loader<CountryPresenter>) {

    }

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<CountryPresenter> {
        return GetPresenter(this, "CountryPresenter")
    }


    private val onQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            getDealsFromDb(query)
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            getDealsFromDb(newText)
            return true
        }

        private fun getDealsFromDb(searchText: String) {
            var searchText = searchText
            searchText = "%$searchText%"

            /*AppDatabase.getInstance(this@GetCountryActivity)!!.countryDao().getSearchList(searchText)
                    .observe(this@GetCountryActivity, object : Observer<List<Country>> {
                        override fun onChanged(@Nullable countrySearchList: List<Country>?) {
                            if (countrySearchList == null) {
                                return
                            }
                            list = countrySearchList
                            countryAdapter = CountryAdapter(list, this@GetCountryActivity)
                            recyclerView.setAdapter(countryAdapter)
                        }
                    })*/

            presenter?.onViewAttached(this@GetCountryActivity)
            presenter?.searchCountry(searchText,searchCountry)!!.observe(this@GetCountryActivity, object : Observer<List<Country>> {
                override fun onChanged(@Nullable countrySearchList: List<Country>?) {
                    if (countrySearchList == null) {
                        return
                    }
                    list = countrySearchList
                    countryAdapter = CountryAdapter(list, this@GetCountryActivity)
                    recyclerView.setAdapter(countryAdapter)
                }
            })
        }
    }

}
