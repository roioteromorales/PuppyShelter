package com.roisoftstudio.puppyshelter.domain.puppies.services;

import com.roisoftstudio.puppyshelter.domain.puppies.repositories.PuppiesRepository;
import com.roisoftstudio.puppyshelter.domain.puppies.model.Puppy;

import java.util.List;

import javax.inject.Inject;

public class PuppiesManagerImpl implements PuppiesManager {

    private PuppiesRepository puppiesRepository;

    @Inject
    public PuppiesManagerImpl( PuppiesRepository puppiesRepository) {
        this.puppiesRepository = puppiesRepository;
    }

    @Override
    public List<Puppy> getAllPuppies() {
        return puppiesRepository.getAllPuppies();
    }

    @Override
    public List<Puppy> getAllPuppiesUrls() {
        return puppiesRepository.getAllPuppiesUrls();
    }
}
