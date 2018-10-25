package com.example.georgeissac.mvp.presentation.interfaces;


import com.example.georgeissac.mvp.database.CountryPojo;

import java.util.List;

public interface CountryContract {

    interface view {
        void showList(List<CountryPojo> list);

        void showError(String error);

        void callDb(String string);

        void callWebService();

        void navigateToShowCountryDetailActivity(CountryPojo country);
    }
}
