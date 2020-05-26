package com.example.apps.items;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.CheckBox;

import com.example.apps.utility.TypeOfProduct;

public class toBuyProduct implements  Parcelable{

    private String name;
    private int amout;
    private TypeOfProduct type;
    private transient CheckBox checkBox;

    public toBuyProduct(String name, int amount, TypeOfProduct type) {
        this.name = name;
        this.amout = amount;
        this.type = type;
        this.checkBox = null;
    }

    protected toBuyProduct(Parcel in) {
        name = in.readString();
        amout = in.readInt();
        type = TypeOfProduct.valueOf(in.readString());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(amout);
        dest.writeString(this.type.name());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<toBuyProduct> CREATOR = new Creator<toBuyProduct>() {
        @Override
        public toBuyProduct createFromParcel(Parcel in) {
            return new toBuyProduct(in);
        }

        @Override
        public toBuyProduct[] newArray(int size) {
            return new toBuyProduct[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getAmout() {
        return amout;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public TypeOfProduct getType() {
        return type;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public boolean isChecked(){
        return checkBox.isChecked();
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", amounth=" + amout +
                ", type=" + type +
                '}';
    }

}
