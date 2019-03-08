package com.simpledev.idog.presenter.impl;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.simpledev.idog.presenter.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Abstract presenter implementation that contains base implementation for other presenters.
 * Subclasses must call super for all {@link BasePresenter} method overriding.
 */
public abstract class BasePresenterImpl<V> implements BasePresenter<V> {
    /**
     * The view
     */
    @Nullable
    private V mView;

    protected CompositeDisposable mDispose = new CompositeDisposable();

    @Override
    public void onViewAttached(@NonNull V view) {
        mView = view;
    }


    @Override
    public void onStart(boolean viewCreated) {
    }

    @Override
    public void onStop() {

    }


    @Override
    public void onViewDetached() {
        mView = null;
    }

    @Override
    public void onPresenterDestroyed() {
        mDispose.dispose();
        mDispose.clear();
    }

    @Override
    public V getView() {
        if(mView == null) {
            throw new IllegalStateException("view not attached");
        }
        return mView;
    }

    @Override
    public void fetchBreedList() {

    }
}
