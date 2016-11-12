package com.roisoftstudio.puppyshelter.domain.puppies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.roisoftstudio.puppyshelter.R;
import com.roisoftstudio.puppyshelter.domain.puppies.PuppyShelterApplication;
import com.roisoftstudio.puppyshelter.domain.puppies.model.Animal;
import com.roisoftstudio.puppyshelter.domain.puppies.retrofit.AnimalService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimalListActivity extends AppCompatActivity {

    @Inject
    AnimalService animalService;

    private RecyclerView mRecyclerView;

    private CardsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list);
        initializeAddFabButton();
        initializeDagger();
        initializeRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    private void initializeAddFabButton() {
        final FloatingActionButton addAnimalButton =  (FloatingActionButton)findViewById(R.id.fab_add_button);
        final Animation rotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        addAnimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAnimalButton.startAnimation(rotateAnimation);
                startActivity(new Intent(AnimalListActivity.this, AnimalAddActivity.class));
            }
        });
    }

    private void initializeRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new CardsAdapter(new ArrayList<Animal>());
        mRecyclerView.setAdapter(adapter);
    }

    private void initializeDagger() {
        PuppyShelterApplication app = (PuppyShelterApplication) getApplication();
        app.getMainComponent().inject(this);
    }

    private void refreshList() {
        animalService.getAllAnimals().enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                if (response.body() != null) {
                    adapter.setPuppies(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                Toast.makeText(AnimalListActivity.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
