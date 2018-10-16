package com.example.georgeissac.mvp

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
import com.example.georgeissac.mvp.presenter.MyPresenter
import com.example.georgeissac.mvp.presenter.PresenterFactory
import com.example.georgeissac.mvp.retrofit.response.Country
import com.example.georgeissac.mvp.room.AppDatabase
import com.example.georgeissac.mvp.view.ViewInterface
import android.support.v4.view.MenuItemCompat.getActionView
import android.support.v7.widget.*
import android.view.Menu


class MainActivity : AppCompatActivity(), PassPositionOfItemClicked, ViewInterface, LoaderManager.LoaderCallbacks<MyPresenter> {

    private val LOADER_ID_PRESENTER = 103
    var presenter: MyPresenter? = null


    lateinit var list: List<Country>
    lateinit var recyclerView: RecyclerView
    lateinit var countryAdapter: CountryAdapter
    private var searchView: SearchView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        presenter?.onViewAttached(this);

    }

    override fun getPositionOfItemForSingleTapUpClick(position: Int) {
        var country = list.get(position)
    }

    override fun showList(result: MutableList<Country>) {
        if (result.size > 0) {
            this.list = result
            countryAdapter = CountryAdapter(list, this@MainActivity)
            Log.e("insert", "insert")

            Thread(Runnable {


                if(AppDatabase.getInstance(this)!!.countryDao().getIfExist(list.get(0).name).size==0){
                    AppDatabase.getInstance(this)!!.countryDao().insert(list)
                }



            }).start()
            recyclerView.setAdapter(countryAdapter)
        }
    }

    override fun showError(error: String) {
        Log.e("error", error)
    }


    override fun onLoadFinished(p0: Loader<MyPresenter>, presenter: MyPresenter?) {
        if (presenter != null) {
            if (presenter is MyPresenter) {
                this.presenter = presenter as MyPresenter

            }
        }
    }

    override fun onLoaderReset(p0: Loader<MyPresenter>) {

    }

    override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<MyPresenter> {
        return GetPresenter(this, MyPresenter())
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

            AppDatabase.getInstance(this@MainActivity)!!.countryDao().getSearchList(searchText)
                    .observe(this@MainActivity, object : Observer<List<Country>> {
                        override fun onChanged(@Nullable countrySearchList: List<Country>?) {
                            if (countrySearchList == null) {
                                return
                            }
                            list = countrySearchList
                            countryAdapter = CountryAdapter(list, this@MainActivity)
                            recyclerView.setAdapter(countryAdapter)
                        }
                    })
        }
    }

}
