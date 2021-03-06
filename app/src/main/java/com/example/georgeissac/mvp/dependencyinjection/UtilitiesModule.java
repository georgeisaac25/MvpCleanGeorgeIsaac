package com.example.georgeissac.mvp.dependencyinjection;

import android.content.Context;

import com.example.georgeissac.mvp.util.Utilities;

import dagger.Module;
import dagger.Provides;

@Module(includes = ContextModule.class)
public class UtilitiesModule {
    @ApplicationScope
    @Provides
    public Utilities getUtilities(Context context){
        return new Utilities(context);
    }
}
