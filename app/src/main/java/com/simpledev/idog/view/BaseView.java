package com.simpledev.idog.view;

public interface BaseView {
    void hideLoading();
    void showLoading();
    void showError(int errorCode);
}
