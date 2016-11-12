package com.roisoftstudio.puppyshelter.domain.puppies.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.roisoftstudio.puppyshelter.R;
import com.roisoftstudio.puppyshelter.domain.puppies.PuppyShelterApplication;
import com.roisoftstudio.puppyshelter.domain.puppies.model.Animal;
import com.roisoftstudio.puppyshelter.domain.puppies.retrofit.AnimalService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.roisoftstudio.puppyshelter.domain.puppies.model.AnimalBuilder.anAnimal;

public class AnimalAddActivity extends AppCompatActivity {

    @Inject
    AnimalService animalService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeDagger();
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle("New Animal");

        initializeAddButton();
    }
    private void initializeDagger() {
        PuppyShelterApplication app = (PuppyShelterApplication) getApplication();
        app.getMainComponent().inject(this);
    }
    private void initializeAddButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameText = (EditText) findViewById(R.id.input_name);
                EditText descriptionText = (EditText) findViewById(R.id.input_description);

                String name = nameText.getText().toString();
                String description = descriptionText.getText().toString();
                Animal newAnimal = anAnimal().withName(name).withDescription(description).createAnimal();
                animalService.save(newAnimal).enqueue(new AddAnimalCallback(view));
            }
        });
    }

    private static class AddAnimalCallback implements Callback<Void> {
        private View view;

        public AddAnimalCallback(View view){
            super();
            this.view = view;
        }

        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Snackbar.make(view, "Saved Animal successfully", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Snackbar.make(view, "Error saving animal: " + t.getMessage(), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
