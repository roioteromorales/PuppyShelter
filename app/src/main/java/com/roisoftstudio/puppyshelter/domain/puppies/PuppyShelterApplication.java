package com.roisoftstudio.puppyshelter.domain.puppies;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.roisoftstudio.puppyshelter.domain.puppies.dependencies.DaggerMainComponent;
import com.roisoftstudio.puppyshelter.domain.puppies.dependencies.MainComponent;


public class PuppyShelterApplication extends Application {

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.create();

    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    @VisibleForTesting
    public void setComponent(MainComponent mainComponent) {
        this.mainComponent = mainComponent;
    }
}