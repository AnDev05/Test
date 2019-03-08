package com.simpledev.idog.presenter;

import com.simpledev.idog.view.AlbumView;

public interface AlbumPresenter extends BasePresenter<AlbumView> {
    void getBreedList();
}