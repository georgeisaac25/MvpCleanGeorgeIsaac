package com.example.georgeissac.mvp.domain.countryUseCase.interfaces;

import com.example.georgeissac.mvp.database.CountryPojo;

import java.util.List;

public interface RepositoryInterface {
    void setResultWhenSucess(List<CountryPojo> result);
    void setResultWhenFailed(String resultWhenFailed);
}
