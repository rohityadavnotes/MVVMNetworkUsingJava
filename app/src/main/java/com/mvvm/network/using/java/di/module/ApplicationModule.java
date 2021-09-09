package com.mvvm.network.using.java.di.module;

import com.mvvm.network.using.java.di.qualifier.TeamAScore;
import com.mvvm.network.using.java.di.qualifier.TeamBScore;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ApplicationModule {

    @Provides
    @TeamAScore
    @Singleton
    int provideSharedPreferencesFileName() {
        return 10;
    }

    @Provides
    @TeamBScore
    @Singleton
    int provideSQLiteDatabaseFileName() {
        return 6;
    }
}
