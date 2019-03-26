package com.simpledev.idog.view;

import android.support.annotation.UiThread;

import com.simpledev.idog.interactor.model.RandomDog;

@UiThread
public interface RandomView extends BaseView{
    void initView(RandomDog dog);
}