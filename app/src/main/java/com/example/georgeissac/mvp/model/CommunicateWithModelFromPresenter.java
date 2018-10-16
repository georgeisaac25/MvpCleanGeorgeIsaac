package com.example.georgeissac.mvp.model;

import com.example.georgeissac.mvp.retrofit.response.Country;

import java.util.List;

/**
 * Created by admin on 20/05/2017.
 */
public interface CommunicateWithModelFromPresenter {
    void setResultWhenSucess(List<Country> result);
    void setResultWhenFailed(String resultWhenFailed);
}
