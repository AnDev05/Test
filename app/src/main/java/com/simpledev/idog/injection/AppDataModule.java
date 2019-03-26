package com.simpledev.idog.injection;

import android.content.Context;

import com.simpledev.idog.network.request.Apis;
import com.simpledev.idog.util.Constants;
import com.simpledev.idog.util.appdata.AppDataHelper;
import com.simpledev.idog.util.appdata.RxAppDataHelper;
import com.simpledev.idog.util.database.DatabaseHelper;
import com.simpledev.idog.util.database.PreferenceHelper;
import com.simpledev.idog.util.database.RxDatabaseHelper;
import com.simpledev.idog.util.database.RxPreferenceHelper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppDataModule {
    @Provides
    @Singleton
    public RxAppDataHelper provideAppDataHelper(@Named(Constants.Apis.DOG_CEO_URL) Apis ceoDogApis, @Named(Constants.Apis.RANDOM_DOG_URL) Apis randomDogApis, RxDatabaseHelper databaseHelper, RxPreferenceHelper preferenceHelper) {
        return new AppDataHelper(ceoDogApis, randomDogApis, databaseHelper, preferenceHelper);
    }
    @Provides
    @Singleton
    public RxDatabaseHelper provideDatabaseHelper(Context context) {
        return DatabaseHelper.getInstance(context, Constants.DbKey.DB_NAME);
    }

    @Provides
    @Singleton
    public RxPreferenceHelper providePreferenceHelper(Context context) {
        return PreferenceHelper.getInstance(context);
    }
}
