package com.simpledev.idog.view;

import android.support.annotation.UiThread;

import java.util.List;

@UiThread
public interface AlbumDetailView extends BaseView{

    void updateBreedDetail(List<String> listBreedImages);
}