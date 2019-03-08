package com.simpledev.idog.util;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.SingleObserver;
import retrofit2.HttpException;
import timber.log.Timber;

public abstract class BaseSingleObserver<T> implements SingleObserver<T> {
    private int mErrorCode = Constants.ErrorCode.NETWORK_ERROR;

    @Override
    public void onError(Throwable e) {
        Timber.e(e);

        if(Commons.isOnline()) {
            if (e instanceof IllegalArgumentException || e instanceof TimeoutException ||
                    e instanceof UnknownHostException || e instanceof ConnectException) {
                mErrorCode = Constants.ErrorCode.ADDRESS_NOT_FOUND;
            }else if (e instanceof HttpException) {
                mErrorCode = ((HttpException) e).code();
            } else {
                mErrorCode = Constants.ErrorCode.OTHER_ERROR;
            }
        }
    }

    protected int getErrorCode() {
        return mErrorCode;
    }
}
