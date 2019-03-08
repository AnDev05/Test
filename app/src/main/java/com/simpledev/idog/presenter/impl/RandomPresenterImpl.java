package com.simpledev.idog.presenter.impl;

import android.support.annotation.NonNull;

import com.simpledev.idog.presenter.RandomPresenter;
import com.simpledev.idog.view.RandomView;
import com.simpledev.idog.interactor.RandomInteractor;

import javax.inject.Inject;

public final class RandomPresenterImpl extends BasePresenterImpl<RandomView> implements RandomPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final RandomInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public RandomPresenterImpl(@NonNull RandomInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        // Your code here. Your view is available using mView and will not be null until next onStop()
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()

        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        /*
         * Your code here. After this method, your presenter (and view) will be completely destroyed
         * so make sure to cancel any HTTP call or database connection
         */

        super.onPresenterDestroyed();
    }
}