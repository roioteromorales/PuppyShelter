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

import com.roisoftstudio.puppyshelter.R;
import com.roisoftstudio.puppyshelter.domain.puppies.model.Puppy;
import com.squareup.picasso.Picasso;

public class PuppyDetailActivity extends AppCompatActivity {
    private static final String PUPPY_KEY = "puppy_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puppy_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Intent intent = getIntent();

        Puppy puppy = (Puppy) intent.getSerializableExtra(PUPPY_KEY);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle(puppy.getName());

        ImageView backdrop = (ImageView) findViewById(R.id.backdrop);
        Picasso.with(getApplicationContext())
                .load(puppy.getImageUrl())
                .placeholder(R.drawable.error_missing_photo)
                .into(backdrop);
    }

    public static void open(Context context, Puppy puppy) {
        Intent intent = new Intent(context, PuppyDetailActivity.class);
        intent.putExtra(PUPPY_KEY, puppy);
        context.startActivity(intent);
    }
}
