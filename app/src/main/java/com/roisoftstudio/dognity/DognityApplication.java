package com.roisoftstudio.dognity;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.roisoftstudio.dognity.dependencies.DaggerMainComponent;
import com.roisoftstudio.dognity.dependencies.MainComponent;


public class DognityApplication extends Application {

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