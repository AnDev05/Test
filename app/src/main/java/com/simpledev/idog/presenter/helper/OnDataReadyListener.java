package com.simpledev.idog.presenter.helper;

import com.simpledev.idog.interactor.model.BaseModel;

import java.util.List;


public interface OnDataReadyListener<T extends BaseModel> {
    void onSuccess(List<T> data);
    void onFailure();
}
