package com.example.georgeissac.mvp.usecase.getCountry.repository;

import com.example.georgeissac.mvp.usecase.getCountry.response.Country;

import java.util.List;

public interface CommunicateFromEntityToInteractor {

    void setResultWhenSucess(List<Country> result);
    void setResultWhenFailed(String resultWhenFailed);

}
