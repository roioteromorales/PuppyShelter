package com.roisoftstudio.dognity.network;

import com.roisoftstudio.dognity.model.Animal;
import com.roisoftstudio.dognity.network.Responses.HttpResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AnimalService {

    @GET("animals")
    Call<List<Animal>> getAllAnimals();

    @POST("animals/add")
    Call<HttpResponse> save(@Body Animal animal);
}
