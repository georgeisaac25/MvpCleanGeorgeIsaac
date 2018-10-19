package com.example.georgeissac.mvp.di;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.georgeissac.mvp.room.AppDatabase;
import com.example.georgeissac.mvp.room.CountryDao;
import com.example.georgeissac.mvp.room.MyRepository;
import com.example.georgeissac.mvp.usecase.addCountry.AddCountry;
import com.example.georgeissac.mvp.usecase.getCountryOnSearch.SearchCountry;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module//(includes = ContextModule.class)
public class DatabaseModule {
    private static final String DB_NAME = "countryDatabase.db";
    private final AppDatabase database;

    public DatabaseModule(Context context){
        this.database = Room.databaseBuilder(
                context,
                AppDatabase.class,
                DB_NAME).build();
    }

    @Provides
    public MyRepository provideRepository(CountryDao repoDao){
        return new MyRepository(repoDao);
    }


    @Provides
    public CountryDao provideDao(AppDatabase database){
        return database.countryDao();
    }

    @Provides
    public AppDatabase provideDb(){
        return database;
    }

    /*@Provides
    @Singleton
    public MyViewModel provideViewModelFactory(MyRepository repository){
        return new MyViewModel(repository);
    }*/


    @Provides
    SearchCountry providesSearchCountry(MyRepository myRepository) {
        return new SearchCountry(myRepository);
    }

    @Provides
    AddCountry providesAddCountry(MyRepository myRepository) {
        return new AddCountry(myRepository);
    }


}
