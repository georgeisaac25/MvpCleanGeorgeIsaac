package com.example.georgeissac.mvp.dataLayer.database;

import android.arch.lifecycle.LiveData;

import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;

public class MyRepository {

    CountryDao repoDao;

    @Inject
    public MyRepository(CountryDao repoDao) {
        this.repoDao = repoDao;
    }

    public List<Country> all() {
        return repoDao.getAll();
    }

    public Maybe<List<Country>> searchByTextUsingRx(String searchString) {
        return repoDao.getSearchListUsingRx(searchString);
    }

    public List<Long> insertData(List<Country> list) {
        return repoDao.insert(list);
    }
}
