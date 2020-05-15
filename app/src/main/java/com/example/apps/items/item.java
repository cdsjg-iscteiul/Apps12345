package com.example.apps.items;

import android.util.Log;

import java.util.ArrayList;

public class item {
    private int mImageResource;
    private String mText1;
    private String mText2;
    private ArrayList<ProductsToBuy> array1;
    private ArrayList<BoughtProduct> array2;


    public item(int imagem, String texto1, ArrayList<BoughtProduct> arrayComprados, ArrayList<ProductsToBuy> arrayComprar) {
        mImageResource = imagem;
        mText1 = texto1;

        Log.d("Carlos" , mText1);

        if(arrayComprar != null){
            Log.d("Carlos" , mText1);
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

    public ArrayList<BoughtProduct> getArray2() {
        return array2;
    }

    public ArrayList<ProductsToBuy> getArray1() {
        return array1;
    }
}

