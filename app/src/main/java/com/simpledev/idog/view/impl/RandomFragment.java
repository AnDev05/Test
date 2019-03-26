package com.simpledev.idog.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simpledev.idog.R;
import com.simpledev.idog.interactor.model.RandomDog;
import com.simpledev.idog.view.RandomView;
import com.simpledev.idog.presenter.loader.PresenterFactory;
import com.simpledev.idog.presenter.RandomPresenter;
import com.simpledev.idog.injection.AppComponent;
import com.simpledev.idog.injection.RandomViewModule;
import com.simpledev.idog.injection.DaggerRandomViewComponent;

import javax.inject.Inject;

public final class RandomFragment extends BaseFragment<RandomPresenter, RandomView> implements RandomView{
    @Inject
    PresenterFactory<RandomPresenter> mPresenterFactory;

    private static RandomFragment mRandomFragment;


    private VideoFragment mVideoFragment;
    private ImageFragment mImageFragment;


    public RandomFragment() {
        // Required empty public constructor
    }


    public static RandomFragment getInstance() {
        if(mRandomFragment == null) {
            mRandomFragment = new RandomFragment();
        }
        return mRandomFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mImageFragment = ImageFragment.newInstance();
        mVideoFragment = VideoFragment.newInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_random, container, false);


        return view;
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

    @Override
    public void initView(RandomDog dog) {
        if (getFragmentManager() != null) {
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            if(dog.getType() == 1) {
                fragmentTransaction.replace(R.id.container_random_layout, mImageFragment).commit();
                mImageFragment.setImageUrl(dog.getUrl());
            }
        }

    }
}
