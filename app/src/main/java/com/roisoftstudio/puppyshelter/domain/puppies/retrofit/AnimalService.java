package com.roisoftstudio.puppyshelter.domain.puppies.retrofit;

import com.roisoftstudio.puppyshelter.domain.puppies.model.Animal;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AnimalService {

    @GET("animals")
    Call<List<Animal>> getAllAnimals();

    @POST("animals/create")
    Call<Void> save(@Body Animal animal);
}
