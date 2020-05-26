package com.example.apps.api;

public class Recipe {

    private int id;
    private String image;
    private String imageType;
    private int likes;
    private int missedIngredientCount;
    private MissedIngredients[] missedIngredients;
    private String title;

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getImageType() {
        return imageType;
    }

    public int getLikes() {
        return likes;
    }

    public int getMissedIngredientCount() {
        return missedIngredientCount;
    }

    public MissedIngredients[] getMissedIngredients() {
        return missedIngredients;
    }

    public String getTitle(){
        return title;
    }
}
