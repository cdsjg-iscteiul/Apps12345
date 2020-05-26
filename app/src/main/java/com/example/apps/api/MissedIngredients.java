package com.example.apps.api;

public class MissedIngredients {
    private String aisle;
    private float amount;
    private int id;
    private String image;
    private String[] meta;
    private String[] metaInformation;
    private String name;
    private String original;
    private String originalName;
    private String unit;
    private String unitLong;
    private String unitShort;

    public String getAisle() {
        return aisle;
    }

    public float getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String[] getMeta() {
        return meta;
    }

    public String getName() {
        return name;
    }

    public String getOriginal() {
        return original;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getUnit() {
        return unit;
    }

    public String getUnitLong() {
        return unitLong;
    }

    public String getUnitShort() {
        return unitShort;
    }

    public String[] getMetaInformation() {
        return metaInformation;
    }
}
