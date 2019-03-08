package com.simpledev.idog.injection;

import android.support.annotation.NonNull;

import com.simpledev.idog.interactor.RandomInteractor;
import com.simpledev.idog.interactor.impl.RandomInteractorImpl;
import com.simpledev.idog.presenter.loader.PresenterFactory;
import com.simpledev.idog.presenter.RandomPresenter;
import com.simpledev.idog.presenter.impl.RandomPresenterImpl;
import com.simpledev.idog.util.appdata.RxAppDataHelper;
import com.simpledev.idog.util.database.RxDatabaseHelper;

import dagger.Module;
import dagger.Provides;

@Module
public final class RandomViewModule {
    @Provides
    public RandomInteractor provideInteractor(RxAppDataHelper rxAppDataHelper) {
        return new RandomInteractorImpl(rxAppDataHelper);
    }

    @Provides
    public PresenterFactory<RandomPresenter> providePresenterFactory(@NonNull final RandomInteractor interactor) {
        return new PresenterFactory<RandomPresenter>() {
            @NonNull
            @Override
            public RandomPresenter create() {
                return new RandomPresenterImpl(interactor);
            }
        };
    }
}
