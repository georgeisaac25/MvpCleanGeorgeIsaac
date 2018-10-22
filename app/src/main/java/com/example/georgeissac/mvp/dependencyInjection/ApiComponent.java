package com.example.georgeissac.mvp.dependencyInjection;


import com.example.georgeissac.mvp.presentationLayer.GetCountryActivity;
import com.example.georgeissac.mvp.retrofit.ApiInterface;

import dagger.Component;

@Component(modules = {DatabaseModule.class,ApiServiceModule.class})
public interface ApiComponent {
   ApiInterface getApiInterface();
    /*MyRepository getRepository();*/


    void inject(GetCountryActivity getCountryActivity);
}
