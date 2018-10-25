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
    SearchCountryUseCase providesSearchCountry(DataRepository dataRepository) {
        return new SearchCountryUseCase(dataRepository);
    }

    @Provides
    AddCountryUseCase providesAddCountry(DataRepository dataRepository) {
        return new AddCountryUseCase(dataRepository);
    }

    @Provides
    GetCountryUseCase providesGetCountryInteractor(DataRepository dataRepository) {
        return new GetCountryUseCase(dataRepository);
    }

    @Provides
    DataRepository provideMyRepository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        return new DataRepository(localDataSource, remoteDataSource);
    }
}
