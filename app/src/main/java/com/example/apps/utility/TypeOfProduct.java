package com.example.apps.utility;

public enum TypeOfProduct {
    amount,Kg,g,L,mL;


    static public TypeOfProduct Type(String t){
        switch (t){
            case "g":
                return g;
            case "Amount":
                return amount;
            case "Kg":
                return Kg;
            case "L":
                return L;
            case "mL":
                return mL;
            default:
                return null;
        }
    }
    static public String toString(TypeOfProduct t) {
        switch (t){
            case g:
                return "g";
            case amount:
                return " ";
            case Kg:
                return "Kg";
            case L:
                return "L";
            case mL:
                return "mL";
            default:
                return "";
        }
    }
    static public int getPosition(TypeOfProduct t){
        switch (t){
            case g:
                return 2;
            case amount:
                return 0;
            case Kg:
                return 1;
            case L:
                return 3;
            case mL:
                return 4;
            default:
                return -1;
        }
    }
}
