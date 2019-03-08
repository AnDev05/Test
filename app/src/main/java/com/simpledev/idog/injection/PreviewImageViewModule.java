package com.simpledev.idog.injection;

import android.support.annotation.NonNull;

import com.simpledev.idog.interactor.PreviewImageInteractor;
import com.simpledev.idog.interactor.impl.PreviewImageInteractorImpl;
import com.simpledev.idog.presenter.loader.PresenterFactory;
import com.simpledev.idog.presenter.PreviewImagePresenter;
import com.simpledev.idog.presenter.impl.PreviewImagePresenterImpl;
import com.simpledev.idog.util.appdata.RxAppDataHelper;

import dagger.Module;
import dagger.Provides;

@Module
public final class PreviewImageViewModule {
    @Provides
    public PreviewImageInteractor provideInteractor(RxAppDataHelper appDataHelper) {
        return new PreviewImageInteractorImpl(appDataHelper);
    }

    @Provides
    public PresenterFactory<PreviewImagePresenter> providePresenterFactory(@NonNull final PreviewImageInteractor interactor) {
        return new PresenterFactory<PreviewImagePresenter>() {
            @NonNull
            @Override
            public PreviewImagePresenter create() {
                return new PreviewImagePresenterImpl(interactor);
            }
        };
    }
}
