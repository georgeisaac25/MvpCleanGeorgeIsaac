package com.example.georgeissac.mvp.domainLayer.getCountry.repository;

import com.example.georgeissac.mvp.domainLayer.getCountry.CountryPojo;

import java.util.List;

public interface CommunicateWithPresenterFromInteractor {
    void setResultWhenSucess(List<CountryPojo> result);
    void setResultWhenFailed(String resultWhenFailed);
}
