package com.example.apps;

import android.util.Log;

import java.util.ArrayList;

public class item {
    private int mImageResource;
    private String mText1;
    private String mText2;
    private ArrayList<ProdutoComprar> array1;
    private ArrayList<ProdutoComprado> array2;


    public item(int imagem, String texto1,ArrayList<ProdutoComprado> arrayComprados, ArrayList<ProdutoComprar> arrayComprar) {
        mImageResource = imagem;
        mText1 = texto1;

        Log.d("Carlos" , mText1);

        if(mText1.equals("Lista de Compras ")){
            Log.d("Carlos" , mText1);
            array1= arrayComprar;
            mText2=  String.valueOf(arrayComprar.size());
        }
        else{
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

    public ArrayList<ProdutoComprado> getArray2() {
        return array2;
    }

    public ArrayList<ProdutoComprar> getArray1() {
        return array1;
    }
}

