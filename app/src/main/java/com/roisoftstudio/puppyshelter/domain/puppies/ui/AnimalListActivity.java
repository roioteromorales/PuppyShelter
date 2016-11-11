package com.roisoftstudio.puppyshelter.domain.puppies.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

    private RecyclerView mRecyclerView;

    @Inject
    AnimalService animalService;
    private ProgressDialog progressDialog;
    private CardsAdapter adapter;
    private Animation rotateAnimation;

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
        rotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        addAnimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAnimalButton.startAnimation(rotateAnimation);
                addAnimalButton.startAnimation(rotateAnimation);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initializeRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new CardsAdapter(new ArrayList<Animal>());
        adapter.setHasStableIds(true);
        mRecyclerView.setAdapter(adapter);
    }

    private void initializeDagger() {
        PuppyShelterApplication app = (PuppyShelterApplication) getApplication();
        app.getMainComponent().inject(this);
    }

    private void refreshList() {
        progressDialog = ProgressDialog.show(AnimalListActivity.this,
                "Please wait", "Getting Puppies...", true, false);
        animalService.getAllAnimals().enqueue(new Callback<List<Animal>>() {
            @Override
            public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    adapter.setPuppies(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Animal>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(AnimalListActivity.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
