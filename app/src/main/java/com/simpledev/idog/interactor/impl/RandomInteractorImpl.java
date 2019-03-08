package com.simpledev.idog.interactor.impl;

import javax.inject.Inject;

import com.simpledev.idog.interactor.RandomInteractor;
import com.simpledev.idog.util.appdata.RxAppDataHelper;

public final class RandomInteractorImpl extends BaseInteractorImpl implements RandomInteractor {
    @Inject
    public RandomInteractorImpl(RxAppDataHelper rxAppDataHelper) {
        super(rxAppDataHelper);
    }
}