package com.example.georgeissac.mvp;

import android.app.Application;

import com.example.georgeissac.mvp.dependencyInjection.AppComponent;
import com.example.georgeissac.mvp.dependencyInjection.ApiServiceModule;
import com.example.georgeissac.mvp.dependencyInjection.ContextModule;
import com.example.georgeissac.mvp.dependencyInjection.DaggerAppComponent;
import com.example.georgeissac.mvp.dependencyInjection.UseCaseModule;
import com.example.georgeissac.mvp.dependencyInjection.UtilitiesModule;

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
