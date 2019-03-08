package com.simpledev.idog.interactor.impl;

import javax.inject.Inject;


import com.simpledev.idog.interactor.AlbumInteractor;

import com.simpledev.idog.interactor.model.Breed;
import com.simpledev.idog.util.appdata.RxAppDataHelper;

import java.util.List;

import io.reactivex.Flowable;


public final class AlbumInteractorImpl extends BaseInteractorImpl implements AlbumInteractor {

    @Inject
    public AlbumInteractorImpl(RxAppDataHelper rxAppDataHelper) {
        super(rxAppDataHelper);
    }

    @Override
    public Flowable<List<Breed>> getBreeds() {
        return mAppDataHelper.getBreedList();
    }
}