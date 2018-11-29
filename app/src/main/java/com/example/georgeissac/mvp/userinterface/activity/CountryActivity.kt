package com.example.georgeissac.mvp.userinterface.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.support.v7.widget.*
import android.view.Menu
import android.view.WindowManager
import android.widget.Toast
import com.example.georgeissac.mvp.MyApp
import com.example.georgeissac.mvp.R
import com.example.georgeissac.mvp.userinterface.interfaces.PositionOfItemClicked
import com.example.georgeissac.mvp.presentation.interfaces.CountryContract
import com.example.domain.domain.CountryPojo
import com.example.domain.domain.countryUseCase.GetCountryUseCase
import com.example.georgeissac.mvp.domain.searchCountryUseCase.SearchCountryUseCase
import com.example.georgeissac.mvp.presentation.presenter.CountryPresenter
import com.example.georgeissac.mvp.userinterface.adapter.CountryAdapter
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
        //window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
        setContentView(R.layout.activity_main)

        if (application is MyApp)
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
            getCountryUseCase
        )

        presenter?.getCountyListUsingRx()
    }

    override fun getPositionOfItemClicked(position: Int) {
        presenter?.selectedCountry(position, list)
    }

    override fun showFreeVersionToast(toastString : String) {
        toastString.showToast()
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
        recyclerView.visibility = View.VISIBLE
        recyclerView.adapter = countryAdapter
    }

    override fun showError(error: String) {
        error.showToast()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        searchView = menu.findItem(R.id.action_searcher).getActionView() as SearchView
        //searchView?.isSubmitButtonEnabled = true
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

    override fun onResume() {
        super.onResume()
        presenter?.showHideSearchbox()
    }

    // just trying extension func
    fun String.showToast(){
        Toast.makeText(this@CountryActivity, this, Toast.LENGTH_LONG).show()
    }
}
