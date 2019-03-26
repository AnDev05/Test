package com.simpledev.idog.presenter;

import com.simpledev.idog.interactor.model.Breed;
import com.simpledev.idog.view.AlbumDetailView;

public interface AlbumDetailPresenter extends BasePresenter<AlbumDetailView> {
    void getAllImagesOfBreed(Breed breed);
}