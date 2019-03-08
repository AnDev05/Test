package com.simpledev.idog.presenter;

import com.simpledev.idog.view.AlbumDetailView;

public interface AlbumDetailPresenter extends BasePresenter<AlbumDetailView> {
    void getAllImagesOfBreed(String baseBreed, String subBreed);
}