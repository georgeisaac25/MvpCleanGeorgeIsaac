package com.example.georgeissac.mvp.domainLayer.getCountry.repository;

import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country;

import java.util.List;

/**
 * Created by admin on 20/05/2017.
 */
public interface CommunicateWithPresenterFromInteractor {
    void setResultWhenSucess(List<Country> result);
    void setResultWhenFailed(String resultWhenFailed);
}