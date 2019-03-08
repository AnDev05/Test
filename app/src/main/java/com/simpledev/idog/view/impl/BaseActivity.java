package com.simpledev.idog.view.impl;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.simpledev.idog.App;
import com.simpledev.idog.R;
import com.simpledev.idog.presenter.loader.PresenterFactory;
import com.simpledev.idog.presenter.loader.PresenterLoader;
import com.simpledev.idog.injection.AppComponent;
import com.simpledev.idog.presenter.BasePresenter;
import com.simpledev.idog.util.Constants;
import com.simpledev.idog.view.BaseView;
import com.simpledev.idog.view.custom.CommonDialog;
import com.simpledev.idog.view.custom.ProgressDialogFragment;

import java.util.concurrent.atomic.AtomicBoolean;

import cn.pedant.SweetAlert.SweetAlertDialog;
import timber.log.Timber;

public abstract class BaseActivity<P extends BasePresenter<V>, V> extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<P>, BaseView {
    /**
     * Do we need to call {@link #doStart()} from the {@link #onLoadFinished(Loader, BasePresenter)} method.
     * Will be true if presenter wasn't loaded when {@link #onStart()} is reached
     */
    private final AtomicBoolean mNeedToCallStart = new AtomicBoolean(false);

    private Dialog mLoadingDialog;

    /**
     * The presenter for this view
     */
    @Nullable
    protected P mPresenter;
    /**
     * Is this the first start of the activity (after onCreate)
     */
    private boolean mFirstStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFirstStart = true;

        injectDependencies();

        getSupportLoaderManager().initLoader(0, null, this).startLoading();
    }

    private void injectDependencies() {
        setupComponent(((App) getApplication()).getAppComponent());
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mPresenter == null) {
            mNeedToCallStart.set(true);
        } else {
            doStart();
        }
    }

    /**
     * Call the presenter callbacks for onStart
     */
    @SuppressWarnings("unchecked")
    private void doStart() {
        assert mPresenter != null;

        mPresenter.onViewAttached((V) this);

        mPresenter.onStart(mFirstStart);

        mFirstStart = false;
    }

    @Override
    protected void onStop() {
        if (mPresenter != null) {
            mPresenter.onStop();

            mPresenter.onViewDetached();
        }

        super.onStop();
    }

    @Override
    public final Loader<P> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(this, getPresenterFactory());
    }

    @Override
    public final void onLoadFinished(Loader<P> loader, P presenter) {
        mPresenter = presenter;

        if (mNeedToCallStart.compareAndSet(true, false)) {
            doStart();
        }
    }

    @Override
    public final void onLoaderReset(Loader<P> loader) {
        mPresenter = null;
    }

    /**
     * Get the presenter factory implementation for this view
     *
     * @return the presenter factory
     */
    @NonNull
    protected abstract PresenterFactory<P> getPresenterFactory();

    /**
     * Setup the injection component for this view
     *
     * @param appComponent the app component
     */
    protected abstract void setupComponent(@NonNull AppComponent appComponent);


    @Override
    public void showLoading() {
        Timber.d("showWaiting()");
        if (mLoadingDialog == null) {
            mLoadingDialog = CommonDialog.createProgressDialog(this);
        }
        if (!this.isFinishing()) {
            mLoadingDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        Timber.d("hideWaiting()");
        if (mLoadingDialog != null && !this.isFinishing()) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void finish() {
        super.finish();
    }

    protected void showCloseAppPopup() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(getString(R.string.confirm))
                .setContentText(getString(R.string.close_msg))
                .setCancelText(getString(R.string.yes))
                .setConfirmText(getString(R.string.no))
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        finish();
                    }
                }).show();
    }

    @Override
    public void showError(int errorCode) {
        String message = "Something went wrong!";
        String title = getString(R.string.opps);
        switch (errorCode) {
            case Constants.ErrorCode.ADDRESS_NOT_FOUND: {
                break;
            }
            case Constants.ErrorCode.NETWORK_ERROR: {
                title = "Network error";
                message = "Please check network connection!";
                break;
            }
            default :{
                message = "Something went wrong!";
                break;
            }
        }
        Context context = App.getAppContext();
        if(context!= null){
            new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText(title)
                    .setContentText(message)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                        }
                    })
                    .setCancelable(true);
        }
    }
}
