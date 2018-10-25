package com.example.georgeissac.mvp.database;


import java.util.List;
import javax.inject.Inject;
import io.reactivex.Maybe;

public class LocalDataSource {

    private CountryDao repoDao;

    @Inject
    public LocalDataSource(CountryDao repoDao) {
        this.repoDao = repoDao;
    }

    public List<CountryPojo> all() {
        return repoDao.getAll();
    }

    public Maybe<List<CountryPojo>> searchByTextUsingRx(String searchString) {
        return repoDao.getSearchListUsingRx(searchString);
    }

    public List<Long> insertData(List<CountryPojo> list) {
        return repoDao.insert(list);
    }
}
