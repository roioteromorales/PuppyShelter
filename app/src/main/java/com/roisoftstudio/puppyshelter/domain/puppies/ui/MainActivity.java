package com.roisoftstudio.puppyshelter.domain.puppies.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.roisoftstudio.puppyshelter.R;
import com.roisoftstudio.puppyshelter.domain.puppies.PuppyShelterApplication;
import com.roisoftstudio.puppyshelter.domain.puppies.model.Puppy;
import com.roisoftstudio.puppyshelter.domain.puppies.services.PuppiesManager;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Inject
    PuppiesManager puppiesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeDagger();
        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter( new CardsAdapter(getPuppies()));
    }

    private void initializeDagger() {
        PuppyShelterApplication app = (PuppyShelterApplication) getApplication();
        app.getMainComponent().inject(this);
    }
    private List<Puppy> getPuppies() {
        return puppiesManager.getAllPuppies();
    }


}
