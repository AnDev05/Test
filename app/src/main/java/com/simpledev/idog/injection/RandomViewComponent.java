package com.simpledev.idog.injection;

import com.simpledev.idog.view.impl.RandomFragment;

import dagger.Component;

@FragmentScope
@Component(dependencies = AppComponent.class, modules = RandomViewModule.class)
public interface RandomViewComponent {
    void inject(RandomFragment fragment);
}