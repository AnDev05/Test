package com.simpledev.idog.injection;

import com.simpledev.idog.util.rx.AppRxSchedulers;
import com.simpledev.idog.util.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

@Module
public class RxModule {
    @Provides
    RxSchedulers proRxSchedulers() {
        return new AppRxSchedulers();
    }
}
