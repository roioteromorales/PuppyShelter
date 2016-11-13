package com.roisoftstudio.dognity.dependencies;

import com.roisoftstudio.dognity.ui.AnimalAddActivity;
import com.roisoftstudio.dognity.ui.AnimalListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(modules = MainModule.class) public interface MainComponent {

  void inject(AnimalListActivity activity);
  void inject(AnimalAddActivity activity);
}
