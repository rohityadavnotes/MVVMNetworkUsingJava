package com.mvvm.network.using.java.di.module;

import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;
import com.mvvm.network.using.java.BuildConfig;
import com.mvvm.network.using.java.data.remote.ApiService;
import com.mvvm.network.using.java.data.remote.RemoteConfiguration;
import com.mvvm.network.using.java.di.qualifier.remote.BaseUrl;
import com.mvvm.network.using.java.di.qualifier.remote.isDebug;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import static android.util.Log.VERBOSE;

@Module
@InstallIn(SingletonComponent.class)
public class RemoteModule {

    @Provides
    @Singleton
    @isDebug
    boolean provideIsDebug() {
        return BuildConfig.DEBUG;
    }

    @Provides
    @Singleton
    @BaseUrl
    String provideBaseUrl() {
        return RemoteConfiguration.BASE_URL;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@isDebug boolean isDebug) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        okHttpClientBuilder.connectTimeout(RemoteConfiguration.HTTP_CONNECT_TIMEOUT, TimeUnit.MINUTES);
        okHttpClientBuilder.writeTimeout(RemoteConfiguration.HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(RemoteConfiguration.HTTP_READ_TIMEOUT, TimeUnit.SECONDS);

        if (isDebug)
        {
            Interceptor interceptor = new LoggingInterceptor.Builder().setLevel(Level.BASIC).log(VERBOSE).build();
            okHttpClientBuilder.addInterceptor(interceptor);
        }

        return okHttpClientBuilder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(@BaseUrl String baseUrl, OkHttpClient okHttpClient) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.client(okHttpClient);
        builder.addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()));
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        return builder.build();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
