package com.simpledev.idog.interactor.impl;



import com.simpledev.idog.interactor.MainInteractor;
import com.simpledev.idog.util.appdata.RxAppDataHelper;

import javax.inject.Inject;


public final class MainInteractorImpl extends BaseInteractorImpl implements MainInteractor {
    @Inject
    public MainInteractorImpl(RxAppDataHelper rxAppDataHelper) {
        super(rxAppDataHelper);
    }
}