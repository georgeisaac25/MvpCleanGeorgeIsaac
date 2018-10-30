package com.example.georgeissac.mvp.dependencyInjection;


import com.example.georgeissac.mvp.retrofit.ApiInterface;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiServiceModule {
    public static final String BASE_URL = "https://restcountries.eu/";

    @ApplicationScope
    @Provides
    public ApiInterface getApiServoce(Retrofit retrofit){
        return retrofit.create(ApiInterface.class);
    }

    @ApplicationScope
    @Provides
    public Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
