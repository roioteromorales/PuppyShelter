package com.roisoftstudio.puppyshelter.domain.puppies.model;

public class AnimalBuilder {
    private String name;
    private String imageUrl;
    private String description;

    public AnimalBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public AnimalBuilder withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public AnimalBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public static AnimalBuilder anAnimal(){
        return new AnimalBuilder();
    }

    public Animal createAnimal() {
        return new Animal(name, imageUrl, description);
    }
}