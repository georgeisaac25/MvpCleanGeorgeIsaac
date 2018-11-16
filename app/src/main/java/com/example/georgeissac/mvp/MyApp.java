package com.example.georgeissac.mvp;

import android.app.Application;

import com.example.georgeissac.mvp.dependencyinjection.AppComponent;
import com.example.georgeissac.mvp.dependencyinjection.ApiServiceModule;
import com.example.georgeissac.mvp.dependencyinjection.ContextModule;
import com.example.georgeissac.mvp.dependencyinjection.DaggerAppComponent;
import com.example.georgeissac.mvp.dependencyinjection.UseCaseModule;
import com.example.georgeissac.mvp.dependencyinjection.UtilitiesModule;

public class MyApp extends Application {
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().apiServiceModule(new ApiServiceModule())
                .useCaseModule(new UseCaseModule(this))
                .contextModule(new ContextModule(this))
                .utilitiesModule(new UtilitiesModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
