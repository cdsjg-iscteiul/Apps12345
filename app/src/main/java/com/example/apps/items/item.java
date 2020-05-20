package com.example.apps.items;

import android.util.Log;

import java.util.ArrayList;

public class item {
    private int mImageResource;
    private String mText1;
    private String mText2;
    private ArrayList<Product> array1;
    private ArrayList<Product> array2;


    public item(int imagem, String texto1, ArrayList<Product> arrayComprados, ArrayList<Product> arrayComprar) {
        mImageResource = imagem;
        mText1 = texto1;

        if(arrayComprar != null){
            array1= arrayComprar;
            mText2=  String.valueOf(arrayComprar.size());
        }
        if(arrayComprados != null){
            array2= arrayComprados;
            mText2=  String.valueOf(arrayComprados.size());
        }
    }

    public int getmImageResource(){
        return mImageResource;
    }

    public String getmText1() {
        return mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public ArrayList<Product> getArray2() {
        return array2;
    }

    public ArrayList<Product> getArray1() {
        return array1;
    }

    public void setArray1(ArrayList<Product> array1) {
        this.array1 = array1;
    }

    public void setArray2(ArrayList<Product> array2) {
        this.array2 = array2;
    }
}

