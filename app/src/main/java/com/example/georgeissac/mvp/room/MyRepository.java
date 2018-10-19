package com.example.georgeissac.mvp.room;



import android.arch.lifecycle.LiveData;

import com.example.georgeissac.mvp.usecase.getCountry.response.Country;

import java.util.List;

import javax.inject.Inject;

public class MyRepository {

    /*
    DatabaseModule databaseModule;*/
    CountryDao repoDao;

    //will inject MyRepository
    @Inject
    public MyRepository(CountryDao repoDao){
        this.repoDao = repoDao;
    }

    public List<Country> getAllFromDatabase(){
        return  repoDao.getAll();
    }

    public LiveData<List<Country>> searchByText(String searchString){
        return  repoDao.getSearchList(searchString);
    }

    public void insertData(List<Country> list){
        repoDao.insert(list);
    }


}
