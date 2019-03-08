package com.simpledev.idog.injection;

import com.simpledev.idog.view.impl.AlbumDetailActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = AlbumDetailViewModule.class)
public interface AlbumDetailViewComponent {
    void inject(AlbumDetailActivity activity);
}