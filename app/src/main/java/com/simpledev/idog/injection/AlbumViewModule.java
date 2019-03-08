package com.simpledev.idog.injection;

import android.support.annotation.NonNull;

import com.simpledev.idog.interactor.AlbumInteractor;
import com.simpledev.idog.interactor.impl.AlbumInteractorImpl;
import com.simpledev.idog.presenter.loader.PresenterFactory;
import com.simpledev.idog.presenter.AlbumPresenter;
import com.simpledev.idog.presenter.impl.AlbumPresenterImpl;
import com.simpledev.idog.util.appdata.RxAppDataHelper;

import dagger.Module;
import dagger.Provides;

@Module
public final class AlbumViewModule {
    @Provides
    public AlbumInteractor provideInteractor(RxAppDataHelper rxAppDataHelper) {
        return new AlbumInteractorImpl(rxAppDataHelper);
    }

    @Provides
    public PresenterFactory<AlbumPresenter> providePresenterFactory(@NonNull final AlbumInteractor interactor) {
        return new PresenterFactory<AlbumPresenter>() {
            @NonNull
            @Override
            public AlbumPresenter create() {
                return new AlbumPresenterImpl(interactor);
            }
        };
    }
}
