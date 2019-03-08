package com.simpledev.idog.util.rx;

import com.simpledev.idog.network.response.BaseResponse;
import com.simpledev.idog.view.BaseView;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public abstract class BaseSingleObserve <V extends BaseView, R extends BaseResponse> implements SingleObserver<R> {

    private V mView;

    public BaseSingleObserve(V view) {
        this.mView = view;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(R response) {

    }

    @Override
    public void onError(Throwable e) {

    }
}
