package com.example.georgeissac.mvp.domain.interfaces;

import com.example.georgeissac.mvp.database.CountryPojo;

import java.util.List;

public interface UseCaseContractInterface {

    void addCountries(List<CountryPojo> list);

    void getCountyList();

    void searchCountry(String string);

}
