package com.example.domain.domain.countryUseCase.interfaces;

import com.example.domain.domain.CountryPojo;
import java.util.List;

public interface RepositoryInterface {
    void setResultWhenSucess(List<CountryPojo> result);
    void setResultWhenFailed(String resultWhenFailed);
}