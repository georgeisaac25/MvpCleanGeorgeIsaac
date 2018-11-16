package com.example.georgeissac.mvp.dependencyinjection;


import com.example.georgeissac.mvp.userinterface.activity.CountryActivity;
import com.example.georgeissac.mvp.userinterface.activity.ShowCountryDetailActivity;
import com.example.georgeissac.mvp.retrofit.ApiInterface;

import dagger.Component;

@ApplicationScope
@Component(modules = {UseCaseModule.class, ApiServiceModule.class, ContextModule.class, UtilitiesModule.class})
public interface AppComponent {
    ApiInterface getApiInterface();

    void inject(CountryActivity countryActivity);

    void inject(ShowCountryDetailActivity showCountryDetailActivity);
}
