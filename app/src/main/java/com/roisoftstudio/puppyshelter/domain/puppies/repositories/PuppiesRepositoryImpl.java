package com.roisoftstudio.puppyshelter.domain.puppies.repositories;

import com.roisoftstudio.puppyshelter.R;
import com.roisoftstudio.puppyshelter.domain.puppies.model.Puppy;

import java.util.Arrays;
import java.util.List;

public class PuppiesRepositoryImpl implements PuppiesRepository {

    @Override
    public List<Puppy> getAllPuppies() {
        return Arrays.asList(
                new Puppy("Hipster Puppy", R.drawable.hipster_puppy),
                new Puppy("Cute Puppy",  R.drawable.cute_puppy),
                new Puppy("Gangsta Puppy",  R.drawable.gangsta_puppy),
                new Puppy("Smart Puppy",  R.drawable.smart_puppy),
                new Puppy("Water Puppy",  R.drawable.water_puppy),
                new Puppy("Swimmer Puppy",  R.drawable.swimmer_puppy)
        );
    }
}
