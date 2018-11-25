package com.example.data.database;


import java.util.List;


import javax.inject.Inject;

import io.reactivex.Maybe;

public class LocalDataSource {

    private CountryDao repoDao;

    @Inject
    public LocalDataSource(CountryDao repoDao) {
        this.repoDao = repoDao;
    }

    public List<CountryEntity> all() {
        return repoDao.getAll();
    }

    public Maybe<List<CountryEntity>> getAllCountryUsingRx() {
        return repoDao.getAllUsingRx();
    }

    public Maybe<List<CountryEntity>> searchByTextUsingRx(String searchString) {
        return repoDao.getSearchListUsingRx(searchString);
    }

    public List<Long> insertData(List<CountryEntity> list) {
        return repoDao.insert(list);
    }
}
