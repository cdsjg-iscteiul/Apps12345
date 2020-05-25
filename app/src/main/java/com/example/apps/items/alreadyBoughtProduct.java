package com.example.apps.items;

import android.os.Parcel;
import android.os.Parcelable;

public class alreadyBoughtProduct implements  Parcelable{

    private String name;
    private int amount;
    private String expire;

    public alreadyBoughtProduct(String name, int quantidade, String expire) {
        this.name = name;
        this.amount = quantidade;
        this.expire = expire;
    }

    protected alreadyBoughtProduct(Parcel in) {
        name = in.readString();
        amount = in.readInt();
        expire = in.readString();
    }

    public static final Creator<alreadyBoughtProduct> CREATOR = new Creator<alreadyBoughtProduct>() {
        @Override
        public alreadyBoughtProduct createFromParcel(Parcel in) {
            return new alreadyBoughtProduct(in);
        }

        @Override
        public alreadyBoughtProduct[] newArray(int size) {
            return new alreadyBoughtProduct[size];
        }
    };

    public String getDate() {
        return expire;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(amount);
        dest.writeString(expire);
    }
}
