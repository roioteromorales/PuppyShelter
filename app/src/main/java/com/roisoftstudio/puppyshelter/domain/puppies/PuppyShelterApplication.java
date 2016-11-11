package com.roisoftstudio.puppyshelter.domain.puppies;

import android.app.Application;
import android.support.annotation.VisibleForTesting;

import com.roisoftstudio.puppyshelter.domain.puppies.dependencies.DaggerMainComponent;
import com.roisoftstudio.puppyshelter.domain.puppies.dependencies.MainComponent;
import com.roisoftstudio.puppyshelter.domain.puppies.retrofit.AnimalService;

import retrofit2.Retrofit;


public class PuppyShelterApplication extends Application {

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://roisoftstudio.com")
                .build();

        AnimalService service = retrofit.create(AnimalService.class);
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    @VisibleForTesting
    public void setComponent(MainComponent mainComponent) {
        this.mainComponent = mainComponent;
    }
}