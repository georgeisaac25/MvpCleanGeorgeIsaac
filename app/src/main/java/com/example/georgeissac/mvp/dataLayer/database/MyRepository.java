package com.example.georgeissac.mvp.dataLayer.database;


import com.example.georgeissac.mvp.domainLayer.getCountry.CountryPojo;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Maybe;

public class MyRepository {

    private CountryDao repoDao;

    @Inject
    public MyRepository(CountryDao repoDao) {
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
