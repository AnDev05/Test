package com.simpledev.idog.view;

import android.support.annotation.UiThread;

import com.simpledev.idog.interactor.model.Breed;

import java.util.List;

@UiThread
public interface AlbumView extends BaseView{

    void updateAlbum(List<Breed> breedList);
}