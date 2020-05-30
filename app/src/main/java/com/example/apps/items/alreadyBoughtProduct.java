package com.example.apps.items;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.apps.utility.TypeOfProduct;

public class alreadyBoughtProduct implements  Parcelable{

    private String name;
    private int amount;
    private TypeOfProduct type;
    private String expire;

    public alreadyBoughtProduct(String name, int quantidade,TypeOfProduct type,  String expire) {
        this.name = name;
        this.amount = quantidade;
        this.type = type;
        this.expire = expire;
    }


    protected alreadyBoughtProduct(Parcel in) {
        name = in.readString();
        amount = in.readInt();
        type = TypeOfProduct.valueOf(in.readString());
        expire = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(amount);
        dest.writeString(this.type.name());
        dest.writeString(expire);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public TypeOfProduct getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }


}
