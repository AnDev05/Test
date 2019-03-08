package com.simpledev.idog;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.simpledev.idog.injection.AppComponent;
import com.simpledev.idog.injection.AppModule;
import com.simpledev.idog.injection.DaggerAppComponent;

import timber.log.Timber;

public final class App extends Application {
    private AppComponent mAppComponent;

    private static Context sContext;


    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(getApplicationContext());

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        sContext = getApplicationContext();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public static Context getAppContext() {
        return sContext;
    }

}