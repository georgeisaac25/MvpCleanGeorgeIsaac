package com.example.georgeissac.mvp.presentation.interfaces;


import com.example.domain.domain.CountryPojo;

import java.util.List;

public interface CountryContract {

    interface view {
        void showList(List<CountryPojo> list);
        void showError(String error);
        void navigateToShowCountryDetailActivity(CountryPojo country);
        void showFreeVersionToast(String  string)    ;
    }
}
