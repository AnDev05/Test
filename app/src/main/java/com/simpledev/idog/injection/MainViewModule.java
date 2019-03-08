package com.simpledev.idog.injection;

import android.support.annotation.NonNull;

import com.simpledev.idog.interactor.MainInteractor;
import com.simpledev.idog.interactor.impl.MainInteractorImpl;
import com.simpledev.idog.presenter.loader.PresenterFactory;
import com.simpledev.idog.presenter.MainPresenter;
import com.simpledev.idog.presenter.impl.MainPresenterImpl;
import com.simpledev.idog.util.appdata.RxAppDataHelper;

import dagger.Module;
import dagger.Provides;

@Module
public final class MainViewModule {
    @Provides
    public MainInteractor provideInteractor(RxAppDataHelper rxAppDataHelper) {
        return new MainInteractorImpl(rxAppDataHelper);
    }

    @Provides
    public PresenterFactory<MainPresenter> providePresenterFactory(@NonNull final MainInteractor interactor) {
        return new PresenterFactory<MainPresenter>() {
            @NonNull
            @Override
            public MainPresenter create() {
                return new MainPresenterImpl(interactor);
            }
        };
    }
}
