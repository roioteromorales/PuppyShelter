/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.roisoftstudio.puppyshelter.domain.puppies.dependencies;

import android.content.Context;

import com.roisoftstudio.puppyshelter.domain.puppies.images.cloudinary.CloudinaryConfigImpl;
import com.roisoftstudio.puppyshelter.domain.puppies.images.cloudinary.CloudinaryServiceImpl;
import com.roisoftstudio.puppyshelter.domain.puppies.images.cloudinary.transformer.ImageTransformer;
import com.roisoftstudio.puppyshelter.domain.puppies.images.cloudinary.transformer.PathToImageTransformer;
import com.roisoftstudio.puppyshelter.domain.puppies.network.AnimalService;
import com.roisoftstudio.puppyshelter.domain.puppies.images.cloudinary.CloudinaryConfig;
import com.roisoftstudio.puppyshelter.domain.puppies.images.cloudinary.CloudinaryService;

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

    @Provides
    @Singleton
    public ImageTransformer provideImageTransformer() {
        return new PathToImageTransformer();
    }
}
