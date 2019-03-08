package com.simpledev.idog.interactor.impl;

import com.simpledev.idog.interactor.PreviewImageInteractor;
import com.simpledev.idog.util.appdata.RxAppDataHelper;

import javax.inject.Inject;

public final class PreviewImageInteractorImpl extends BaseInteractorImpl implements PreviewImageInteractor {
    @Inject
    public PreviewImageInteractorImpl(RxAppDataHelper rxAppDataHelper) {
        super(rxAppDataHelper);
    }
}