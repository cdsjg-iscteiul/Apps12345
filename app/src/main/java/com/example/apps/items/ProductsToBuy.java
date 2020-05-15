package com.example.apps.items;

import androidx.annotation.NonNull;

public class ProductsToBuy {

    private String nome;
    private String quantidade;

    public void ProdutoPorComprar(String nome, String quantidade){
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public String getQuantidade() {
        return quantidade;
    }


}
