package com.example.apps.items;

public class BoughtProduct {

    private String nome;
    private int quantidade;
    private int dia;
    private int mes;
    private int ano;
    private String tipo;

    public void BoughtProduct(String nome, int quantidade ,int dia, int mes, int ano, String tipo){
        this.nome = nome;
        this.quantidade = quantidade;
        this.dia = dia;
        this.mes= mes;
        this.ano= ano;
    }


    public int getDia() {
        return dia;
    }

    public int getAno() {
        return ano;
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

    public int getMes() {
        return mes;
    }


}
