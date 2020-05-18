package com.example.apps.items;

import android.widget.CheckBox;

import com.example.apps.utility.TypeOfProduct;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int amount;
    private TypeOfProduct type;
    private CheckBox checkBox;

    public Product(String name, int amount, TypeOfProduct type) {
        this.name = name;
        this.amount = amount;
        this.type = type;
        this.checkBox = null;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isChecked(){
        return checkBox.isChecked();
    }

    public TypeOfProduct getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", amounth=" + amount +
                ", type=" + type +
                '}';
    }
}
