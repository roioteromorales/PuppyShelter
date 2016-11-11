package com.roisoftstudio.puppyshelter.domain.puppies.services;

import com.roisoftstudio.puppyshelter.domain.puppies.model.Animal;
import com.roisoftstudio.puppyshelter.domain.puppies.repositories.AnimalsRepository;

import java.util.List;

import javax.inject.Inject;

public class AnimalsManagerImpl implements AnimalsManager {

    private AnimalsRepository animalsRepository;

    @Inject
    public AnimalsManagerImpl(AnimalsRepository animalsRepository) {
        this.animalsRepository = animalsRepository;
    }

    @Override
    public List<Animal> getAllPuppies() {
        return animalsRepository.getAllAnimals();
    }
}
