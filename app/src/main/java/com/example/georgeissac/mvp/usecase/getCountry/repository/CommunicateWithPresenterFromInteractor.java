package com.example.georgeissac.mvp.usecase.getCountry.repository;

import com.example.georgeissac.mvp.usecase.getCountry.response.Country;

import java.util.List;

/**
 * Created by admin on 20/05/2017.
 */
public interface CommunicateWithPresenterFromInteractor {
    void setResultWhenSucess(List<Country> result);
    void setResultWhenFailed(String resultWhenFailed);
}
