package com.simpledev.idog.injection;

import com.simpledev.idog.view.impl.AlbumFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = AlbumViewModule.class)
public interface AlbumViewComponent {
    void inject(AlbumFragment fragment);
}