package com.roisoftstudio.dognity.dependencies;

import com.roisoftstudio.dognity.domain.dognity.images.cloudinary.CloudinaryConfig;
import com.roisoftstudio.dognity.domain.dognity.images.cloudinary.CloudinaryConfigImpl;
import com.roisoftstudio.dognity.domain.dognity.images.cloudinary.CloudinaryService;
import com.roisoftstudio.dognity.domain.dognity.images.cloudinary.CloudinaryServiceImpl;
import com.roisoftstudio.dognity.network.AnimalService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MainModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://roisoftstudio.com:18080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public AnimalService provideAnimalService(Retrofit retrofit) {
        return retrofit.create(AnimalService.class);
    }

    @Provides
    @Singleton
    public CloudinaryConfig provideCloudinaryConfig() {
        return new CloudinaryConfigImpl();
    }

    @Provides
    @Singleton
    public CloudinaryService provideCloudinaryService(CloudinaryConfig cloudinaryConfig) {
        return new CloudinaryServiceImpl(cloudinaryConfig);
    }
}
