package com.roisoftstudio.puppyshelter.domain.puppies.model;


import java.io.Serializable;

public class Puppy implements Serializable{
    private int imageId;
    private String name;
    private String description;

    public Puppy(String name, int imageResource) {
        this.name = name;
        this.imageId = imageResource;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
