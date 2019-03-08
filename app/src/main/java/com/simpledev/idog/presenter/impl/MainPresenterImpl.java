package com.simpledev.idog.presenter.impl;

import android.support.annotation.NonNull;

import com.simpledev.idog.interactor.model.Breed;
import com.simpledev.idog.presenter.MainPresenter;
import com.simpledev.idog.presenter.helper.OnDataReadyListener;
import com.simpledev.idog.view.MainView;
import com.simpledev.idog.interactor.MainInteractor;

import java.util.List;

import javax.inject.Inject;


public final class MainPresenterImpl extends BasePresenterImpl<MainView> implements MainPresenter {

    @NonNull
    private final MainInteractor mInteractor;

    @Inject
    public MainPresenterImpl(@NonNull MainInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);

        if(viewCreated && !mInteractor.checkResourceOk()) {
            fetchBreedList();
        }
    }

    @Override
    public void onStop() {
        // Your code here, mView will be null after this method until next onStart()

        super.onStop();
    }

    @Override
    public void onPresenterDestroyed() {
        super.onPresenterDestroyed();
    }

    @Override
    public void fetchBreedList() {
        if(!mInteractor.checkResourceOk()){
            getView().showLoading();
            mInteractor.initResource(new OnDataReadyListener<Breed>() {
                @Override
                public void onSuccess(List<Breed> data) {
                    getView().hideLoading();
                }

                @Override
                public void onFailure() {
                    getView().hideLoading();
                }
            });
        }
    }
}