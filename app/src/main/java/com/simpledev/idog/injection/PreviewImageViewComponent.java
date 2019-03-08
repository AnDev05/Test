package com.simpledev.idog.injection;

import com.simpledev.idog.view.impl.PreviewImageActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = PreviewImageViewModule.class)
public interface PreviewImageViewComponent {
    void inject(PreviewImageActivity activity);
}