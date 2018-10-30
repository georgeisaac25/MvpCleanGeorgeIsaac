package com.example.georgeissac.mvp.dependencyInjection;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.georgeissac.mvp.data.DataRepository;
import com.example.georgeissac.mvp.remote.RemoteDataSource;
import com.example.georgeissac.mvp.database.LocalDataSource;
import com.example.georgeissac.mvp.database.AppDatabase;
import com.example.georgeissac.mvp.database.CountryDao;
import com.example.georgeissac.mvp.domain.addCountryUseCase.AddCountryUseCase;
import com.example.georgeissac.mvp.domain.countryUseCase.GetCountryUseCase;
import com.example.georgeissac.mvp.domain.searchCountryUseCase.SearchCountryUseCase;
import com.example.georgeissac.mvp.retrofit.ApiInterface;

import dagger.Module;
import dagger.Provides;

@Module(includes = ApiServiceModule.class)
public class UseCaseModule {
    private static final String DB_NAME = "countryDatabase.db";
    private final AppDatabase database;

    public UseCaseModule(Context context) {
        this.database = Room.databaseBuilder(
                context,
                AppDatabase.class,
                DB_NAME).build();
    }

    @ApplicationScope
    @Provides
    RemoteDataSource provideRemoteDataSource(ApiInterface apiInterface) {
        return new RemoteDataSource(apiInterface);
    }

    @ApplicationScope
    @Provides
    LocalDataSource provideRepository(CountryDao repoDao) {
        return new LocalDataSource(repoDao);
    }

    @ApplicationScope
    @Provides
    CountryDao provideDao(AppDatabase database) {
        return database.countryDao();
    }

    @ApplicationScope
    @Provides
    AppDatabase provideDb() {
        return database;
    }

    @ApplicationScope
    @Provides
    SearchCountryUseCase providesSearchCountry(DataRepository dataRepository) {
        return new SearchCountryUseCase(dataRepository);
    }

    @ApplicationScope
    @Provides
    AddCountryUseCase providesAddCountry(DataRepository dataRepository) {
        return new AddCountryUseCase(dataRepository);
    }

    @ApplicationScope
    @Provides
    GetCountryUseCase providesGetCountryInteractor(DataRepository dataRepository) {
        return new GetCountryUseCase(dataRepository);
    }

    @ApplicationScope
    @Provides
    DataRepository provideMyRepository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        return new DataRepository(localDataSource, remoteDataSource);
    }
}
