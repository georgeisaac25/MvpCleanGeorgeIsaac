package com.example.georgeissac.mvp.domainLayer.getCountry.repository;

import com.example.georgeissac.mvp.domainLayer.getCountry.response.Country;

import java.util.List;

public interface CommunicateWithPresenterFromInteractor {
    void setResultWhenSucess(List<Country> result);
    void setResultWhenFailed(String resultWhenFailed);
}
