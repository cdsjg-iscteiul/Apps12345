package com.example.apps.items;

import androidx.annotation.NonNull;

public class ProductsToBuy {

    private String nome;
    private int quantidade;
    private String tipo;

    public void ProductsToBuy(String nome, int quantidade, String tipo){
        this.nome = nome;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getTipo() {
        return tipo;
    }
}
