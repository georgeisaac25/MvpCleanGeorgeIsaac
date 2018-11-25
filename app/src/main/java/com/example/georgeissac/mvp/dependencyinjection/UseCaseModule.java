package com.example.georgeissac.mvp.dependencyinjection;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.data.database.AppDatabase;
import com.example.data.database.CountryDao;
import com.example.data.database.LocalDataSource;
import com.example.data.datasource.DataRepository;
import com.example.data.remote.RemoteDataSource;
import com.example.data.remote.retrofit.ApiInterface;
import com.example.domain.domain.interfaces.RepositoryContract;

import com.example.domain.domain.countryUseCase.GetCountryUseCase;
import com.example.georgeissac.mvp.domain.searchCountryUseCase.SearchCountryUseCase;

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
                DB_NAME).allowMainThreadQueries().build();
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
    SearchCountryUseCase providesSearchCountry(RepositoryContract dataRepository) {
        return new SearchCountryUseCase(dataRepository);
    }

    /*@ApplicationScope
    @Provides
    AddCountryUseCase providesAddCountry(RepositoryContract dataRepository) {
        return new AddCountryUseCase(dataRepository);
    }*/

    @ApplicationScope
    @Provides
    GetCountryUseCase providesGetCountryInteractor(RepositoryContract dataRepository) {
        return new GetCountryUseCase(dataRepository);
    }

    @ApplicationScope
    @Provides
    RepositoryContract provideMyRepository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        return new DataRepository(localDataSource, remoteDataSource);
    }
}
