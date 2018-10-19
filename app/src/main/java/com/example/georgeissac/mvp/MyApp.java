package com.example.georgeissac.mvp;

import android.app.Application;
import android.arch.persistence.room.RoomDatabase;

import com.example.georgeissac.mvp.di.ApiComponent;
import com.example.georgeissac.mvp.di.ApiServiceModule;
import com.example.georgeissac.mvp.di.DaggerApiComponent;
import com.example.georgeissac.mvp.di.DatabaseModule;
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
