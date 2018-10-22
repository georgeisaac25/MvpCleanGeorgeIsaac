package com.example.georgeissac.mvp;

import android.app.Application;

import com.example.georgeissac.mvp.dependencyInjection.ApiComponent;
import com.example.georgeissac.mvp.dependencyInjection.ApiServiceModule;
import com.example.georgeissac.mvp.dependencyInjection.DaggerApiComponent;
import com.example.georgeissac.mvp.dependencyInjection.DatabaseModule;
import com.example.georgeissac.mvp.retrofit.ApiInterface;

public class MyApp extends Application {

    //private RetrofitComponent mApiComponent;

    ApiComponent apiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
       /* mApiComponent = DaggerRetrofitComponent.builder()
                .appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule())
                .build();*/

        apiComponent = DaggerApiComponent.builder().apiServiceModule(new ApiServiceModule())
                .databaseModule(new DatabaseModule(this))
                .build();


    }

    public ApiInterface getNetComponent() {
        return apiComponent.getApiInterface();
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }

    /*public RoomDatabase getRoomDB() {
        return apiComponent.getRoomDb();
    }*/
}
