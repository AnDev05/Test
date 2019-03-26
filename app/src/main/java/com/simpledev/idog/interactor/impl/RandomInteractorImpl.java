package com.simpledev.idog.interactor.impl;

import javax.inject.Inject;

import com.simpledev.idog.interactor.RandomInteractor;
import com.simpledev.idog.interactor.model.RandomDog;
import com.simpledev.idog.network.response.RandomDogResponse;
import com.simpledev.idog.util.appdata.RxAppDataHelper;

import io.reactivex.Single;

public final class RandomInteractorImpl extends BaseInteractorImpl implements RandomInteractor {
    @Inject
    public RandomInteractorImpl(RxAppDataHelper rxAppDataHelper) {
        super(rxAppDataHelper);
    }

    @Override
    public Single<RandomDogResponse> loadRandomDog() {
        return mAppDataHelper.getRandomDog();
    }
}