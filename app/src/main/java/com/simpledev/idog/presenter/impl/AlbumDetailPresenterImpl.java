package com.simpledev.idog.presenter.impl;

import android.support.annotation.NonNull;

import com.simpledev.idog.interactor.model.Breed;
import com.simpledev.idog.network.response.BreedResponse;
import com.simpledev.idog.presenter.AlbumDetailPresenter;
import com.simpledev.idog.util.BaseSingleObserver;
import com.simpledev.idog.util.Commons;
import com.simpledev.idog.view.AlbumDetailView;
import com.simpledev.idog.interactor.AlbumDetailInteractor;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public final class AlbumDetailPresenterImpl extends BasePresenterImpl<AlbumDetailView> implements AlbumDetailPresenter {
    /**
     * The interactor
     */
    @NonNull
    private final AlbumDetailInteractor mInteractor;

    // The view is available using the mView variable

    @Inject
    public AlbumDetailPresenterImpl(@NonNull AlbumDetailInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void onStart(boolean viewCreated) {
        super.onStart(viewCreated);
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
    public void getAllImagesOfBreed(final Breed breed) {
        getView().showLoading();

        if(Commons.isNullOrEmpty(breed.getSubBreed())) {
            mInteractor
                    .getAllImagesOfBreed(breed.getBaseBreed())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseSingleObserver<BreedResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mDispose.add(d);
                        }

                        @Override
                        public void onSuccess(BreedResponse breedResponse) {
                            Timber.d("On get breeds success");
                            getView().hideLoading();
                            getView().updateBreedDetail(breedResponse.getListBreedImages());

                            mInteractor.saveNumberOfBreed(breed.getId(), breedResponse.getListBreedImages().size());
                        }
                        @Override
                        public void onError(Throwable e) {
                            Timber.d("On get breeds error");
                            super.onError(e);
                            getView().hideLoading();
                            getView().showError(getErrorCode());
                        }
                    });

        }else{
            mInteractor
                    .getAllImagesOfBreed(breed.getBaseBreed(), breed.getSubBreed())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseSingleObserver<BreedResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            mDispose.add(d);
                        }

                        @Override
                        public void onSuccess(BreedResponse breedResponse) {
                            Timber.d("On get breeds success");
                            getView().hideLoading();
                            getView().updateBreedDetail(breedResponse.getListBreedImages());
                            mInteractor.saveNumberOfBreed(breed.getId(), breedResponse.getListBreedImages().size());
                        }

                        @Override
                        public void onError(Throwable e) {
                            Timber.d("On get breeds success");
                            getView().hideLoading();
                            getView().showError(getErrorCode());
                        }
                    });
        }
    }
}