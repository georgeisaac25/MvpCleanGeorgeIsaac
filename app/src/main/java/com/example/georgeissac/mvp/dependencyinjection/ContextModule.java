package com.example.georgeissac.mvp.dependencyinjection;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    Context context;
    public ContextModule(Context context){
        this.context = context;
    }

    @ApplicationScope
    @Provides
    Context getContext(){
        return context;
    }
}
