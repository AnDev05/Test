package com.simpledev.idog.interactor;

import com.simpledev.idog.interactor.model.Breed;

import java.util.List;

import io.reactivex.Flowable;


public interface AlbumInteractor extends BaseInteractor {

    Flowable<List<Breed>> getBreeds();
}