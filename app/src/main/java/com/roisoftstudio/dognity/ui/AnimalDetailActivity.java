package com.roisoftstudio.dognity.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.roisoftstudio.dognity.R;
import com.roisoftstudio.dognity.model.Animal;
import com.squareup.picasso.Picasso;

public class AnimalDetailActivity extends AppCompatActivity {
    private static final String ANIMAL_KEY = "animal_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeFloatingActionButton();

        initializeUIElements();
    }

    private void initializeUIElements() {
        Animal animal = getAnimalFromIntent();
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle(animal.getName());

        TextView description = (TextView) findViewById(R.id.animal_detail_description);
        description.setText(animal.getDescription());

        ImageView backdrop = (ImageView) findViewById(R.id.animal_detail_backdrop);
        Picasso.with(this)
                .load(animal.getImageUrl())
                .placeholder(R.drawable.error_missing_photo)
                .into(backdrop);
    }

    private void initializeFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_detail_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private Animal getAnimalFromIntent() {
        return (Animal) getIntent().getSerializableExtra(ANIMAL_KEY);
    }

    public static void open(Context context, Animal animal) {
        Intent intent = new Intent(context, AnimalDetailActivity.class);
        intent.putExtra(ANIMAL_KEY, animal);
        context.startActivity(intent);
    }
}
