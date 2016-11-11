package com.roisoftstudio.puppyshelter.domain.puppies.model;


import java.io.Serializable;

public class Puppy implements Serializable{
    private String name;
    private String imageUrl;
    private String description;

    Puppy(String name, String imageUrl, String description) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
