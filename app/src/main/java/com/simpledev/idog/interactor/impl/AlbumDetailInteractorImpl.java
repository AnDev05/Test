package com.simpledev.idog.interactor.impl;

import javax.inject.Inject;

import com.simpledev.idog.interactor.AlbumDetailInteractor;
import com.simpledev.idog.interactor.model.Breed;
import com.simpledev.idog.network.response.BreedResponse;
import com.simpledev.idog.util.appdata.RxAppDataHelper;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public final class AlbumDetailInteractorImpl extends BaseInteractorImpl implements AlbumDetailInteractor {

    @Inject
    public AlbumDetailInteractorImpl(RxAppDataHelper rxAppDataHelper) {
        super(rxAppDataHelper);
    }

    @Override
    public Single<BreedResponse> getAllImagesOfBreed(String baseBreed, String subBreed) {
        return mAppDataHelper.getAllImageOfBreed(baseBreed,subBreed);
    }

    @Override
    public Single<BreedResponse> getAllImagesOfBreed(String baseBreed) {
        return mAppDataHelper.getAllImageOfBreed(baseBreed);
    }

    @Override
    public void saveNumberOfBreed(Long id, int size) {
        mAppDataHelper.saveNumberOfBreed(id, size);
    }
}