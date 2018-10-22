package com.example.georgeissac.mvp.presentationLayer

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.util.Log
import android.view.View
import android.support.v7.widget.*
import android.view.Menu
import android.widget.Toast
import com.example.georgeissac.mvp.MyApp
import com.example.georgeissac.mvp.R
import com.example.georgeissac.mvp.presentationLayer.adapter.CountryAdapter
import com.example.georgeissac.mvp.presentationLayer.interfaces.PassPositionOfItemClicked
import com.example.georgeissac.mvp.presentationLayer.interfaces.ViewInterface
import com.example.georgeissac.mvp.presentationLayer.presenterUtil.GetPresenter
import com.example.georgeissac.mvp.retrofit.ApiInterface
import com.example.georgeissac.mvp.domainLayer.addCountry.AddCountry
import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country
import com.example.georgeissac.mvp.domainLayer.getCountryOnSearch.SearchCountry
import com.example.georgeissac.mvp.util.Utilities
import javax.inject.Inject


class GetCountryActivity : AppCompatActivity(), PassPositionOfItemClicked, ViewInterface, LoaderManager.LoaderCallbacks<CountryPresenter> {

    private val LOADER_ID_PRESENTER = 103
    var presenter: CountryPresenter? = null

    lateinit var list: List<Country>
    lateinit var recyclerView: RecyclerView
    lateinit var countryAdapter: CountryAdapter
    private var searchView: SearchView? = null

    @Inject
    lateinit var searchCountry: SearchCountry

    @Inject
    lateinit var addCountry: AddCountry


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


    override fun onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        presenter?.onViewAttached(this)
        if (Utilities.isNetAvailable(this)) {
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
        val country = list.get(position)

        val intent = Intent(this, ShowActivity::class.java)
        intent.putExtra("countryImg", country.flag)
        intent.putExtra("countryName", country.name)
        startActivity(intent)
    }

    override fun showList(result: MutableList<Country>) {
        if (result.size > 0) {
            this.list = result
            Log.e("GetCountryActivity", "insert")
            presenter?.addCountries(list,addCountry = addCountry)
            callAdapter(list)
        }
    }

    override fun showError(error: String) {
        Log.e("error", error)
        Toast.makeText(this,"Please try again",Toast.LENGTH_LONG).show()
    }

    override fun showListFromDB(result: List<Country>) {
        this.list = result
        callAdapter(list)
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)

        searchView = menu.findItem(R.id.action_search)
                .getActionView() as SearchView

        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(onQueryTextListener)

        return super.onCreateOptionsMenu(menu)
    }

    private val onQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String): Boolean {
            getCountryFromDb(query)
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            getCountryFromDb(newText)
            return true
        }

        private fun getCountryFromDb(textToSearch : String) {
            var searchText = textToSearch
            searchText = "%$searchText%"
            search(searchText)
        }
    }

    fun search(searchText: String) {
        presenter?.searchCountry(searchText,searchCountry)
    }

    fun callAdapter (list: List<Country>) {
        if(list.isNotEmpty()) {
            countryAdapter = CountryAdapter(this, list, this@GetCountryActivity)
            recyclerView.adapter = countryAdapter
        }
    }

}
