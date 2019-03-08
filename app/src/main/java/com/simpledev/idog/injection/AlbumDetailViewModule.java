package com.simpledev.idog.injection;

import android.support.annotation.NonNull;

import com.simpledev.idog.interactor.AlbumDetailInteractor;
import com.simpledev.idog.interactor.impl.AlbumDetailInteractorImpl;
import com.simpledev.idog.presenter.loader.PresenterFactory;
import com.simpledev.idog.presenter.AlbumDetailPresenter;
import com.simpledev.idog.presenter.impl.AlbumDetailPresenterImpl;
import com.simpledev.idog.util.appdata.RxAppDataHelper;

import dagger.Module;
import dagger.Provides;

@Module
public final class AlbumDetailViewModule {
    @Provides
    public AlbumDetailInteractor provideInteractor(RxAppDataHelper appDataHelper) {
        return new AlbumDetailInteractorImpl(appDataHelper);
    }

    @Provides
    public PresenterFactory<AlbumDetailPresenter> providePresenterFactory(@NonNull final AlbumDetailInteractor interactor) {
        return new PresenterFactory<AlbumDetailPresenter>() {
            @NonNull
            @Override
            public AlbumDetailPresenter create() {
                return new AlbumDetailPresenterImpl(interactor);
            }
        };
    }
}
