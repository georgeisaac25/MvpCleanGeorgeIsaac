package com.example.georgeissac.mvp.dependencyInjection;


import com.example.georgeissac.mvp.userInterface.activity.CountryActivity;
import com.example.georgeissac.mvp.userInterface.activity.ShowCountryDetailActivity;
import com.example.georgeissac.mvp.retrofit.ApiInterface;

import dagger.Component;

@ApplicationScope
@Component(modules = {UseCaseModule.class, ApiServiceModule.class, ContextModule.class, UtilitiesModule.class})
public interface AppComponent {
    ApiInterface getApiInterface();

    void inject(CountryActivity countryActivity);

    void inject(ShowCountryDetailActivity showCountryDetailActivity);
}
