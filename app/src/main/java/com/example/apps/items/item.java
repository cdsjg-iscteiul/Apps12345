package com.example.apps.items;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

public class item implements Parcelable {
    private int mImageResource;
    private String mText1;
    private String mText2;
    private ArrayList<alreadyBoughtProduct> arrayComprados;
    private ArrayList<toBuyProduct> arrayComprar;


    public item(int imagem, String texto1, ArrayList<alreadyBoughtProduct> arrayComprados, ArrayList<toBuyProduct> arrayComprar) {
        mImageResource = imagem;
        mText1 = texto1;

        if(arrayComprar != null){
            this.arrayComprar= arrayComprar;
            mText2=  String.valueOf(arrayComprar.size());
        }
        if(arrayComprados != null){
            this.arrayComprados= arrayComprados;
            mText2=  String.valueOf(arrayComprados.size());
        }
    }

    protected item(Parcel in) {
        mImageResource = in.readInt();
        mText1 = in.readString();
        mText2 = in.readString();
    }

    public static final Creator<item> CREATOR = new Creator<item>() {
        @Override
        public item createFromParcel(Parcel in) {
            return new item(in);
        }

        @Override
        public item[] newArray(int size) {
            return new item[size];
        }
    };

    public int getmImageResource(){
        return mImageResource;
    }

    public String getmText1() {
        return mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public ArrayList<toBuyProduct> getArrayComprar() {
        return arrayComprar;
    }

    public ArrayList<alreadyBoughtProduct> getArrayComprados() {
        return arrayComprados;
    }

    public void setArray1(ArrayList<alreadyBoughtProduct> array1) {
        this.arrayComprados = array1;
    }

    public void setArray2(ArrayList<toBuyProduct> array2) {
        this.arrayComprar = array2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mImageResource);
        dest.writeString(mText1);
        dest.writeString(mText2);
    }
}

