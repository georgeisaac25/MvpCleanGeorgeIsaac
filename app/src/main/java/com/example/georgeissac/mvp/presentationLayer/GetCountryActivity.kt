package com.example.georgeissac.mvp.presentationLayer

import android.arch.lifecycle.Observer
import android.content.Intent
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
import android.widget.Toast
import com.example.georgeissac.mvp.MyApp
import com.example.georgeissac.mvp.R
import com.example.georgeissac.mvp.retrofit.ApiInterface
import com.example.georgeissac.mvp.room.MyRepository
import com.example.georgeissac.mvp.usecase.addCountry.AddCountry
import com.example.georgeissac.mvp.usecase.getCountry.response.Country
import com.example.georgeissac.mvp.usecase.getCountryOnSearch.SearchCountry
import com.example.georgeissac.mvp.util.Utlities
import okhttp3.internal.Util
import javax.inject.Inject


class GetCountryActivity : AppCompatActivity(), PassPositionOfItemClicked, ViewInterface, LoaderManager.LoaderCallbacks<CountryPresenter> {

    private val LOADER_ID_PRESENTER = 103
    var presenter: CountryPresenter? = null


    lateinit var list: List<Country>
    lateinit var recyclerView: RecyclerView
    lateinit var countryAdapter: CountryAdapter
    private var searchView: SearchView? = null

    @Inject
    lateinit var
            retrofitInstance: ApiInterface

    @Inject
    lateinit var
            repo: MyRepository


    @Inject
    lateinit var
            searchCountry: SearchCountry

    @Inject
    lateinit var
            addCountry: AddCountry


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MyApp).apiComponent.inject(this)

        recyclerView = findViewById(R.id.recyclerView)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        val myToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(myToolbar)

        supportLoaderManager.initLoader(LOADER_ID_PRESENTER, null, this);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)

        searchView = menu.findItem(R.id.action_search)
                .getActionView() as SearchView

        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(onQueryTextListener)

        return super.onCreateOptionsMenu(menu)
    }


    override fun onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        presenter?.onViewAttached(this)
        if (Utlities.isNetAvailable(this)) {
            presenter?.getCountyList()
        } else {
            var searchText = ""
            searchText = "%$searchText%"
            search(searchText)
        }
    }

    override fun onStop() {
        presenter?.onViewDetached()
        super.onStop()
    }

    override fun getPositionOfItemForSingleTapUpClick(position: Int) {
        var country = list.get(position)

        val intent = Intent(this, ShowActivity::class.java)
        intent.putExtra("countryImg", country.flag)
        intent.putExtra("countryName", country.name)
        startActivity(intent)
    }

    override fun showList(result: MutableList<Country>) {
        if (result.size > 0) {
            this.list = result
            countryAdapter = CountryAdapter(this, list, this@GetCountryActivity)
            Log.e("GetCountryActivity", "insert")

            presenter?.onViewAttached(this@GetCountryActivity)
            presenter?.addCountries(list,addCountry = addCountry)
            recyclerView.adapter = countryAdapter
        }
    }

    override fun showError(error: String) {
        Log.e("error", error)
    }

    override fun showListFromDB(result: List<Country>) {
        this.list = result
        countryAdapter = CountryAdapter(this, list, this@GetCountryActivity)
        recyclerView.adapter = countryAdapter
    }


    override fun onLoadFinished(p0: Loader<CountryPresenter>, presenter: CountryPresenter?) {
        if (presenter != null) {
            this.presenter = presenter
        }
    }

    override fun onLoaderReset(p0: Loader<CountryPresenter>) {
        presenter = null
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
            presenter?.onViewAttached(this@GetCountryActivity)
            search(searchText)
        }


    }

    fun search(searchText: String) {

        presenter?.searchCountry(searchText,searchCountry)
        /* Works well with live data
        presenter?.searchCountry(searchText, searchCountry)!!.observe(this@GetCountryActivity, object : Observer<List<Country>> {
            override fun onChanged(@Nullable countrySearchList: List<Country>?) {
                if (countrySearchList == null) {
                    return
                }
                list = countrySearchList
                if (list.size > 0) {
                    countryAdapter = CountryAdapter(this@GetCountryActivity, list, this@GetCountryActivity)
                    recyclerView.setAdapter(countryAdapter)
                } else {
                    Toast.makeText(this@GetCountryActivity, "No List", Toast.LENGTH_LONG).show()
                }
            }
        })*/

        presenter?.searchCountry(searchText,searchCountry)
    }

}
