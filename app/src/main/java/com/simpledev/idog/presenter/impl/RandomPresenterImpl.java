package com.simpledev.idog.presenter.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.simpledev.idog.interactor.model.RandomDog;
import com.simpledev.idog.network.response.BreedResponse;
import com.simpledev.idog.network.response.RandomDogResponse;
import com.simpledev.idog.presenter.RandomPresenter;
import com.simpledev.idog.util.BaseSingleObserver;
import com.simpledev.idog.util.Commons;
import com.simpledev.idog.view.RandomView;
import com.simpledev.idog.interactor.RandomInteractor;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
        if(viewCreated) {
            getRandomDog();
        }
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

    @Override
    public void getRandomDog() {
        getView().showLoading();
        mInteractor.loadRandomDog().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSingleObserver<RandomDogResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(RandomDogResponse randomDogResponse) {
                        getView().hideLoading();

                        RandomDog dog = new RandomDog();
                        dog.setUrl(randomDogResponse.getUrl());
                        dog.setType(Commons.getType(randomDogResponse.getUrl()));

                        getView().initView(dog);

                    }
                });
    }
}