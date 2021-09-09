package com.mvvm.network.using.java.di.module;

import com.mvvm.network.using.java.di.qualifier.local.RealmDatabaseName;
import com.mvvm.network.using.java.di.qualifier.local.RoomDatabaseName;
import com.mvvm.network.using.java.di.qualifier.local.SQLiteDatabaseName;
import com.mvvm.network.using.java.di.qualifier.local.SharedPreferencesName;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class LocalModule {

    @Provides
    @SharedPreferencesName
    @Singleton
    String provideSharedPreferencesFileName() {
        return "";
    }

    @Provides
    @SQLiteDatabaseName
    @Singleton
    String provideSQLiteDatabaseFileName() {
        return "";
    }

    @Provides
    @RoomDatabaseName
    @Singleton
    String provideRoomDatabaseFileName() {
        return "";
    }

    @Provides
    @RealmDatabaseName
    @Singleton
    String provideRealmDatabaseFileName() {
        return "";
    }
}
