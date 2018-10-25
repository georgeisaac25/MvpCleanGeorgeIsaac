package com.example.georgeissac.mvp.dependencyInjection;

import android.arch.persistence.room.Room;
import android.content.Context;
import com.example.georgeissac.mvp.dataLayer.DataRepository;
import com.example.georgeissac.mvp.dataLayer.RemoteDataSource;
import com.example.georgeissac.mvp.dataLayer.database.LocalDataSource;
import com.example.georgeissac.mvp.dataLayer.database.AppDatabase;
import com.example.georgeissac.mvp.dataLayer.database.CountryDao;
import com.example.georgeissac.mvp.domainLayer.addCountry.AddCountry;
import com.example.georgeissac.mvp.domainLayer.getCountryOnSearch.SearchCountry;
import com.example.georgeissac.mvp.retrofit.ApiInterface;
import dagger.Module;
import dagger.Provides;

@Module(includes = ApiServiceModule.class)
public class DatabaseModule {
    private static final String DB_NAME = "countryDatabase.db";
    private final AppDatabase database;

    public DatabaseModule(Context context) {
        this.database = Room.databaseBuilder(
                context,
                AppDatabase.class,
                DB_NAME).build();
    }

    @Provides
    RemoteDataSource provideRemoteDataSource(ApiInterface apiInterface) {
        return new RemoteDataSource(apiInterface);
    }

    @Provides
    LocalDataSource provideRepository(CountryDao repoDao) {
        return new LocalDataSource(repoDao);
    }

    @Provides
    CountryDao provideDao(AppDatabase database) {
        return database.countryDao();
    }

    @Provides
    AppDatabase provideDb() {
        return database;
    }

    @Provides
    SearchCountry providesSearchCountry(DataRepository dataRepository) {
        return new SearchCountry(dataRepository);
    }

    @Provides
    AddCountry providesAddCountry(DataRepository dataRepository) {
        return new AddCountry(dataRepository);
    }

    @Provides
    DataRepository provideMyRepository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        return new DataRepository(localDataSource, remoteDataSource);
    }
}
