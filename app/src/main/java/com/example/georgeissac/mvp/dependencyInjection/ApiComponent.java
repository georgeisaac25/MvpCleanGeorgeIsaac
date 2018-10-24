package com.example.georgeissac.mvp.dependencyInjection;


import com.example.georgeissac.mvp.presentationLayer.GetCountryActivity;
import com.example.georgeissac.mvp.presentationLayer.ShowCountryDetailActivity;
import com.example.georgeissac.mvp.retrofit.ApiInterface;

import dagger.Component;

@Component(modules = {DatabaseModule.class, ApiServiceModule.class, ContextModule.class, UtilitiesModule.class})
public interface ApiComponent {
    ApiInterface getApiInterface();

    void inject(GetCountryActivity getCountryActivity);

    void inject(ShowCountryDetailActivity showCountryDetailActivity);
}
