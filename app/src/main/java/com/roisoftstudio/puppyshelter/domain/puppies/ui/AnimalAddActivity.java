package com.roisoftstudio.puppyshelter.domain.puppies.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.roisoftstudio.puppyshelter.R;
import com.roisoftstudio.puppyshelter.domain.puppies.PuppyShelterApplication;
import com.roisoftstudio.puppyshelter.domain.puppies.images.UploadCallback;
import com.roisoftstudio.puppyshelter.domain.puppies.images.cloudinary.CloudinaryService;
import com.roisoftstudio.puppyshelter.domain.puppies.model.Animal;
import com.roisoftstudio.puppyshelter.domain.puppies.network.AnimalService;
import com.roisoftstudio.puppyshelter.domain.puppies.ui.callbacks.AddAnimalCallback;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.inject.Inject;

import static android.content.Intent.ACTION_PICK;
import static android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
import static com.roisoftstudio.puppyshelter.domain.puppies.model.AnimalBuilder.anAnimal;

public class AnimalAddActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE = 999;

    @Inject
    AnimalService animalService;
    @Inject
    CloudinaryService cloudinaryServiceImpl;

    private String uploadedImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeDagger();
        initializeCollapsingToolbar();
        initializeAddPhotoButton();
        initializeSaveButton();
    }

    private void initializeDagger() {
        PuppyShelterApplication app = (PuppyShelterApplication) getApplication();
        app.getMainComponent().inject(this);
    }

    private void initializeCollapsingToolbar() {
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle("New Animal");
    }

    private void initializeAddPhotoButton() {
        FloatingActionButton addPhotoButton = (FloatingActionButton) findViewById(R.id.add_photo_button);
        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ACTION_PICK, EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });
    }

    private void initializeSaveButton() {
        Button buttonSave = (Button) findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ((EditText) findViewById(R.id.input_name)).getText().toString();
                String description = ((EditText) findViewById(R.id.input_description)).getText().toString();

                Animal newAnimal = anAnimal()
                        .withName(name)
                        .withDescription(description)
                        .withImageUrl(uploadedImageUrl).createAnimal();
                animalService.save(newAnimal).enqueue(new AddAnimalCallback(view));
                hideKeyboard(view);
            }

            private void hideKeyboard(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            try {
                InputStream imageInputStream = this.getContentResolver().openInputStream(data.getData());
                cloudinaryServiceImpl.upload(imageInputStream, new UploadCallback() {
                    @Override
                    public void onResponse(String url) {
                        uploadedImageUrl = url;
                        Picasso.with(AnimalAddActivity.this)
                                .load(uploadedImageUrl)
                                .placeholder(R.drawable.error_missing_photo)
                                .into((ImageView) findViewById(R.id.animal_add_backdrop));
                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
