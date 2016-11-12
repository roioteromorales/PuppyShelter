package com.roisoftstudio.puppyshelter.domain.puppies.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.roisoftstudio.puppyshelter.R;
import com.roisoftstudio.puppyshelter.domain.puppies.PuppyShelterApplication;
import com.roisoftstudio.puppyshelter.domain.puppies.model.Animal;
import com.roisoftstudio.puppyshelter.domain.puppies.network.AnimalService;
import com.roisoftstudio.puppyshelter.domain.puppies.network.cloudinary.CloudinaryService;
import com.roisoftstudio.puppyshelter.domain.puppies.network.cloudinary.CloudinaryServiceImpl;
import com.roisoftstudio.puppyshelter.domain.puppies.network.Responses.HttpResponse;
import com.squareup.picasso.Picasso;

import java.util.Random;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.roisoftstudio.puppyshelter.domain.puppies.model.AnimalBuilder.anAnimal;

public class AnimalAddActivity extends AppCompatActivity {
    private static final String TAG = "AnimalAddActivity";

    @Inject
    AnimalService animalService;
    @Inject
    CloudinaryService cloudinaryServiceImpl;

    private String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeDagger();
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle("New Animal");
        initializeAddPhotoButton();
        initializeSaveButton();
    }

    private void initializeSaveButton() {
        Button buttonSave = (Button) findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText) findViewById(R.id.input_name)).getText().toString();
                String description = ((EditText) findViewById(R.id.input_description)).getText().toString();
                ImageView backdrop = (ImageView) findViewById(R.id.animal_add_backdrop);

                Animal newAnimal = anAnimal()
                        .withName(name)
                        .withDescription(description)
                        .withImageUrl(imageUrl).createAnimal();
                animalService.save(newAnimal).enqueue(new AddAnimalCallback(view));
                hideKeyboard(view);
            }

            private void hideKeyboard(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
    }

    private void initializeDagger() {
        PuppyShelterApplication app = (PuppyShelterApplication) getApplication();
        app.getMainComponent().inject(this);
    }

    private void initializeAddPhotoButton() {
        FloatingActionButton addPhotoButton = (FloatingActionButton) findViewById(R.id.add_photo_button);
        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView backdrop = (ImageView) findViewById(R.id.animal_add_backdrop);
                imageUrl = getImageUrl();
                Picasso.with(AnimalAddActivity.this)
                        .load(imageUrl)
                        .placeholder(R.drawable.error_missing_photo)
                        .into(backdrop);
            }

            @NonNull
            private String getImageUrl() {
                return "https://unsplash.it/640/480?image=" + new Random().nextInt(1000);
            }
        });
    }

    private static class AddAnimalCallback implements Callback<HttpResponse> {
        private View view;

        public AddAnimalCallback(View view) {
            super();
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
}
