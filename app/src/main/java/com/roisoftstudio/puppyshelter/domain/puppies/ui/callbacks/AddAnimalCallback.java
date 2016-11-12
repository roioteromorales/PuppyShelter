package com.roisoftstudio.puppyshelter.domain.puppies.ui.callbacks;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.roisoftstudio.puppyshelter.domain.puppies.network.Responses.HttpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAnimalCallback implements Callback<HttpResponse> {
    private static final String TAG = "AddAnimalCallback";

    private View view;

    public AddAnimalCallback(View view) {
        this.view = view;
    }

    @Override
    public void onResponse(Call<HttpResponse> call, Response<HttpResponse> response) {
        String message = response.body().getStatus() + ":" + response.body().getMessage();
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onFailure(Call<HttpResponse> call, Throwable t) {
        Log.e(TAG, t.getMessage() + ":", t);
        Snackbar.make(view, "Error saving animal: " + t.getMessage(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
