package com.roisoftstudio.puppyshelter.domain.puppies.ui;

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

import com.roisoftstudio.puppyshelter.R;
import com.roisoftstudio.puppyshelter.domain.puppies.model.Animal;
import com.squareup.picasso.Picasso;

public class AnimalDetailActivity extends AppCompatActivity {
    private static final String PUPPY_KEY = "puppy_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puppy_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeFloatingActionButton();

        Animal animal = getPuppy();
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle(animal.getName());

        TextView description = (TextView) findViewById(R.id.animal_detail_description);
        description.setText(animal.getDescription());

        ImageView backdrop = (ImageView) findViewById(R.id.backdrop);
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

    private Animal getPuppy() {
        Intent intent = getIntent();
        return (Animal) intent.getSerializableExtra(PUPPY_KEY);
    }

    public static void open(Context context, Animal animal) {
        Intent intent = new Intent(context, AnimalDetailActivity.class);
        intent.putExtra(PUPPY_KEY, animal);
        context.startActivity(intent);
    }
}
