package com.example.georgeissac.mvp.presentationLayer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
import com.example.georgeissac.mvp.domainLayer.addCountry.AddCountry
import com.example.georgeissac.mvp.domainLayer.getCountry.CountryPojo
import com.example.georgeissac.mvp.domainLayer.getCountry.GetCountryInteractor
import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country
import com.example.georgeissac.mvp.domainLayer.getCountryOnSearch.SearchCountry
import com.example.georgeissac.mvp.util.Utilities
import javax.inject.Inject


class GetCountryActivity : AppCompatActivity(), PassPositionOfItemClicked, ViewInterface/*, LoaderManager.LoaderCallbacks<CountryPresenter>*/ {

    private var presenter: CountryPresenter? = null
    lateinit var list: List<CountryPojo>
    lateinit var recyclerView: RecyclerView
    lateinit var countryAdapter: CountryAdapter
    private var searchView: SearchView? = null

    @Inject
    lateinit var searchCountry: SearchCountry

    @Inject
    lateinit var addCountry: AddCountry

    @Inject
    lateinit var getCountryInteractor: GetCountryInteractor

    @Inject
    lateinit var utilities: Utilities

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

        presenter = CountryPresenter(this, searchCountry, addCountry, getCountryInteractor ,utilities)
        presenter?.getData()
    }

    override fun callDb(searchText: String) {
        presenter?.searchCountry(searchText)
    }

    override fun callWebService() {
        presenter?.getCountyList()
    }

    override fun getPositionOfItemForSingleTapUpClick(position: Int) {
        presenter?.getSelectedCountry(position, list)
    }

    override fun navigateToShowCountryDetailActivity(country: CountryPojo?) {
        val intent = Intent(this, ShowCountryDetailActivity::class.java)
        intent.putExtra("countryImg", country?.flag)
        intent.putExtra("countryName", country?.name)
        startActivity(intent)
    }

    override fun showList(list: List<CountryPojo>) {
        Log.e("GetCountryActivity", "insert")
        this.list = list
        presenter?.addCountries(list)
        countryAdapter = CountryAdapter(list, this@GetCountryActivity, utilities)
        recyclerView.adapter = countryAdapter
    }

    override fun showError(error: String) {
        Log.e("error", error)
        Toast.makeText(this, "Please try again", Toast.LENGTH_LONG).show()
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
        override fun onQueryTextSubmit(searchText: String): Boolean {
            presenter?.searchInDb(searchText)
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            presenter?.searchInDb(newText)
            return true
        }
    }
}
