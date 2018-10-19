package com.example.georgeissac.mvp.di;


import com.example.georgeissac.mvp.presentationLayer.GetCountryActivity;
import com.example.georgeissac.mvp.retrofit.ApiInterface;
import com.example.georgeissac.mvp.room.AppDatabase;
import com.example.georgeissac.mvp.room.MyRepository;

import dagger.Component;

@Component(modules = {DatabaseModule.class,ApiServiceModule.class})
public interface ApiComponent {
   ApiInterface getApiInterface();
    /*MyRepository getRepository();*/


    void inject(GetCountryActivity getCountryActivity);
}
