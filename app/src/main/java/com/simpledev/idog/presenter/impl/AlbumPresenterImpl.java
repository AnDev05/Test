package com.simpledev.idog.presenter.impl;

import android.support.annotation.NonNull;
import android.view.View;

import com.simpledev.idog.interactor.model.Breed;
import com.simpledev.idog.presenter.AlbumPresenter;
import com.simpledev.idog.presenter.helper.OnDataReadyListener;
import com.simpledev.idog.util.Constants;
import com.simpledev.idog.util.database.PreferenceHelper;
import com.simpledev.idog.view.AlbumView;
import com.simpledev.idog.interactor.AlbumInteractor;


import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import timber.log.Timber;


public final class AlbumPresenterImpl extends BasePresenterImpl<AlbumView> implements AlbumPresenter {

    @NonNull
    private final AlbumInteractor mInteractor;

    @Inject
    public AlbumPresenterImpl(@NonNull AlbumInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
        if(viewCreated && mInteractor.checkResourceOk()){
            getBreedList();
        }
    }

    @Override
    public void onStop() {
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
                    getBreedList();
                }

                @Override
                public void onFailure() {
                    getView().hideLoading();
                }
            });
        }
    }

    @Override
    public void getBreedList() {
        getView().showLoading();

        Flowable<List<Breed>> breeds = mInteractor.getBreeds();

        breeds.subscribe(new Consumer<List<Breed>>() {
            @Override
            public void accept(List<Breed> breeds) {
                Timber.d("Load ok");
                if(mInteractor.checkResourceOk(breeds.size())){
                    getView().updateAlbum(breeds);
                }else{
                    //Reset data size
                    mInteractor.setNumberResource(0);
                    fetchBreedList();
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                Timber.d("Error");
            }
        }, new Action() {
            @Override
            public void run() {
                getView().hideLoading();
                fetchBreedList();
            }
        });
    }
}