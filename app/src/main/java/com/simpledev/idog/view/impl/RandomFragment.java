package com.simpledev.idog.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simpledev.idog.R;
import com.simpledev.idog.view.RandomView;
import com.simpledev.idog.presenter.loader.PresenterFactory;
import com.simpledev.idog.presenter.RandomPresenter;
import com.simpledev.idog.injection.AppComponent;
import com.simpledev.idog.injection.RandomViewModule;
import com.simpledev.idog.injection.DaggerRandomViewComponent;

import javax.inject.Inject;

public final class RandomFragment extends BaseFragment<RandomPresenter, RandomView> implements RandomView {
    @Inject
    PresenterFactory<RandomPresenter> mPresenterFactory;

    // Your presenter is available using the mPresenter variable

    public RandomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_random, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Your code here
        // Do not call mPresenter from here, it will be null! Wait for onStart
    }

    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerRandomViewComponent.builder()
                .appComponent(parentComponent)
                .randomViewModule(new RandomViewModule())
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected PresenterFactory<RandomPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }
}
