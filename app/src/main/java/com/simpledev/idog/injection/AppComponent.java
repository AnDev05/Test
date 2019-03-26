package com.simpledev.idog.injection;

import android.content.Context;

import com.simpledev.idog.App;
import com.simpledev.idog.network.request.Apis;
import com.simpledev.idog.util.Commons;
import com.simpledev.idog.util.Constants;
import com.simpledev.idog.util.appdata.RxAppDataHelper;
import com.simpledev.idog.util.database.RxPreferenceHelper;
import com.simpledev.idog.util.rx.RxSchedulers;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class, RxModule.class, AppDataModule.class})
public interface AppComponent {
    Context getAppContext();

    App getApp();

    RxSchedulers rxSchedulers();

    RxAppDataHelper getAppdataHelper();

    RxPreferenceHelper getPreference();
}