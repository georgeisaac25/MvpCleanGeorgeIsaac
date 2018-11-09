package com.example.georgeissac.mvp.userInterface.activity

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
import com.example.georgeissac.mvp.userInterface.interfaces.PositionOfItemClicked
import com.example.georgeissac.mvp.presentation.interfaces.CountryContract
import com.example.georgeissac.mvp.domain.CountryPojo
import com.example.georgeissac.mvp.domain.countryUseCase.GetCountryUseCase
import com.example.georgeissac.mvp.domain.searchCountryUseCase.SearchCountryUseCase
import com.example.georgeissac.mvp.presentation.presenter.CountryPresenter
import com.example.georgeissac.mvp.userInterface.adapter.CountryAdapter
import com.example.georgeissac.mvp.util.Constants
import com.example.georgeissac.mvp.util.Utilities
import javax.inject.Inject

class CountryActivity : AppCompatActivity(),
    PositionOfItemClicked,
    CountryContract.view {

    private var presenter: CountryPresenter? = null
    lateinit var list: List<CountryPojo>
    lateinit var recyclerView: RecyclerView
    lateinit var countryAdapter: CountryAdapter
    private var searchView: SearchView? = null

    @Inject
    lateinit var searchCountryUseCase: SearchCountryUseCase

    @Inject
    lateinit var getCountryUseCase: GetCountryUseCase

    @Inject
    lateinit var utilities: Utilities

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MyApp).appComponent.inject(this)

        recyclerView = findViewById(R.id.recyclerView)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        val myToolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(myToolbar)

        presenter = CountryPresenter(
            this,
            searchCountryUseCase,
            getCountryUseCase,
            utilities
        )
        presenter?.getCountyList()
    }

    override fun callWebService() {
        presenter?.getCountyList()
    }

    override fun getPositionOfItemClicked(position: Int) {
        presenter?.getSelectedCountry(position, list)
    }

    override fun navigateToShowCountryDetailActivity(country: CountryPojo?) {
        val intent = Intent(this, ShowCountryDetailActivity::class.java)
        intent.putExtra(Constants.countryImg, country?.flag)
        intent.putExtra(Constants.countryName, country?.name)
        startActivity(intent)
    }

    override fun showList(list: List<CountryPojo>) {
        this.list = list
        countryAdapter = CountryAdapter(list, this@CountryActivity, utilities)
        recyclerView.adapter = countryAdapter
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        searchView = menu.findItem(R.id.action_search).getActionView() as SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(onQueryTextListener)
        return super.onCreateOptionsMenu(menu)
    }

    private val onQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            presenter?.searchInDb(newText)
            return true
        }
    }

    override fun onStop() {
        presenter?.onStop()
        super.onStop()
    }
}
