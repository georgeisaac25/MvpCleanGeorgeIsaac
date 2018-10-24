package com.example.georgeissac.mvp;

import android.app.Application;

import com.example.georgeissac.mvp.dependencyInjection.ApiComponent;
import com.example.georgeissac.mvp.dependencyInjection.ApiServiceModule;
import com.example.georgeissac.mvp.dependencyInjection.ContextModule;
import com.example.georgeissac.mvp.dependencyInjection.DaggerApiComponent;
import com.example.georgeissac.mvp.dependencyInjection.DatabaseModule;
import com.example.georgeissac.mvp.dependencyInjection.UtilitiesModule;
import com.example.georgeissac.mvp.retrofit.ApiInterface;

public class MyApp extends Application {
    ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        apiComponent = DaggerApiComponent.builder().apiServiceModule(new ApiServiceModule())
                .databaseModule(new DatabaseModule(this))
                .contextModule(new ContextModule(this))
                .utilitiesModule(new UtilitiesModule())
                .build();
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }
}
