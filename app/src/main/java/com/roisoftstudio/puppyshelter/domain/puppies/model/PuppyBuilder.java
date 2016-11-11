package com.roisoftstudio.puppyshelter.domain.puppies.model;

public class PuppyBuilder {
    private String name;
    private String imageUrl;
    private String description;

    public PuppyBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public PuppyBuilder withImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public PuppyBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public static PuppyBuilder aPuppy(){
        return new PuppyBuilder();
    }

    public Puppy createPuppy() {
        return new Puppy(name, imageUrl, description);
    }
}