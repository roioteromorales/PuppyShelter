package com.roisoftstudio.puppyshelter.domain.puppies.dependencies;

import com.roisoftstudio.puppyshelter.domain.puppies.ui.AnimalAddActivity;
import com.roisoftstudio.puppyshelter.domain.puppies.ui.AnimalListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton @Component(modules = MainModule.class) public interface MainComponent {

  void inject(AnimalListActivity activity);
  void inject(AnimalAddActivity activity);
}
