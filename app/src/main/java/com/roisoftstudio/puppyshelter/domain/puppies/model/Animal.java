package com.roisoftstudio.puppyshelter.domain.puppies.model;


import java.io.Serializable;

public class Animal implements Serializable{
    private String name;
    private String imageUrl;
    private String description;

    public Animal() {
    }

    public Animal(String name, String imageUrl, String description) {
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

    public String getDescription() {
        return description;
    }
}
