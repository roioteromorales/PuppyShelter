package com.roisoftstudio.puppyshelter.domain.puppies.repositories;

import com.roisoftstudio.puppyshelter.domain.puppies.model.Puppy;

import java.util.List;

public interface PuppiesRepository {
    List<Puppy> getAllPuppies();
}
