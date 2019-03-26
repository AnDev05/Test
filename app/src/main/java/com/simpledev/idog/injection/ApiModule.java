package com.simpledev.idog.injection;

import android.content.Context;

import com.simpledev.idog.network.request.Apis;
import com.simpledev.idog.util.Constants;

import java.io.File;
import java.util.concurrent.TimeUnit;


import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    @Provides
    @Singleton
    @Named(Constants.Apis.DOG_CEO_URL)
    Apis providedDogCEOService(OkHttpClient client, GsonConverterFactory gson, RxJava2CallAdapterFactory rxAdapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Apis.DOG_CEO_URL)
                .client(client)
                .addConverterFactory(gson)
                .addCallAdapterFactory(rxAdapter)
                .build();

        return retrofit.create(Apis.class);
    }

    @Provides
    @Singleton
    @Named(Constants.Apis.RANDOM_DOG_URL)
    Apis provideRandomDogService(OkHttpClient client, GsonConverterFactory gson, RxJava2CallAdapterFactory rxAdapter) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.Apis.RANDOM_DOG_URL)
                .client(client)
                .addConverterFactory(gson)
                .addCallAdapterFactory(rxAdapter)
                .build();

        return retrofit.create(Apis.class);
    }

    @Provides
    OkHttpClient proHttpClient(Cache cache, HttpLoggingInterceptor logging) {
        return new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .cache(cache)
                .build();
    }

    @Provides
    HttpLoggingInterceptor provideInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Provides
    Cache provideCache(File file) {
        return new Cache(file, 10 * 10 * 1000);
    }

    @Provides
    File provideCacheFile(Context context) {
        return context.getFilesDir();
    }

    @Provides
    RxJava2CallAdapterFactory provideRxAdapter() {
        return RxJava2CallAdapterFactory.create();
    }

    @Provides
    GsonConverterFactory provideGsonClient() {
        return GsonConverterFactory.create();
    }
}
