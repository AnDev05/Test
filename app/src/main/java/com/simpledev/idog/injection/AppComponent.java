package com.simpledev.idog.injection;

import android.content.Context;

import com.simpledev.idog.App;
import com.simpledev.idog.network.request.Apis;
import com.simpledev.idog.util.appdata.RxAppDataHelper;
import com.simpledev.idog.util.database.RxPreferenceHelper;
import com.simpledev.idog.util.rx.RxSchedulers;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class, RxModule.class, AppDataModule.class})
public interface AppComponent {
    Context getAppContext();

    App getApp();

    Apis appApis();

    RxSchedulers rxSchedulers();

    RxAppDataHelper getAppdataHelper();

    RxPreferenceHelper getPreference();
}