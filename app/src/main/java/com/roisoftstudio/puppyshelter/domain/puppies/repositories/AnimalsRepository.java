package com.roisoftstudio.puppyshelter.domain.puppies.repositories;

import com.roisoftstudio.puppyshelter.domain.puppies.model.Animal;

import java.util.List;

public interface AnimalsRepository {
    List<Animal> getAllAnimals();
}
