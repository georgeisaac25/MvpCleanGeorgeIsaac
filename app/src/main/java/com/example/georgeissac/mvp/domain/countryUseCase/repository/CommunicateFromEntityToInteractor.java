package com.example.georgeissac.mvp.domain.countryUseCase.repository;

import com.example.georgeissac.mvp.database.CountryPojo;

import java.util.List;

public interface CommunicateFromEntityToInteractor {
    void setResultWhenSucess(List<CountryPojo> result);
    void setResultWhenFailed(String resultWhenFailed);
}
