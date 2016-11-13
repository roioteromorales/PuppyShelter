package com.roisoftstudio.dognity.model;

public class AnimalBuilder {
    private int id;
    private int ownerId;
    private String name;
    private String description;
    private String type;

    //Non Mandatory
    private String imageUrl;
    private String breed;
    private String age;
    private String gender;
    private String location;

    public AnimalBuilder(int ownerId, String name, String description, String type) {
        this.ownerId = ownerId;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public AnimalBuilder withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public AnimalBuilder withBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public AnimalBuilder withAge(String age) {
        this.age = age;
        return this;
    }

    public AnimalBuilder withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public AnimalBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    public static AnimalBuilder anAnimal(int ownerId, String name, String description, String type){
        return new AnimalBuilder(ownerId, name, description, type);
    }

    public Animal createAnimal() {
        Animal animal = new Animal(ownerId, name, description, type);
        animal.setImageUrl(imageUrl);
        animal.setBreed(breed);
        animal.setAge(age);
        animal.setGender(gender);
        animal.setLocation(location);
        return animal;
    }
}