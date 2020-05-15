package com.example.apps;

public class ProdutoComprado {

    private String nome;
    private String quantidade;
    private int dia;
    private int mes;
    private int ano;

    public void ProdutoComprado(String nome, String quantidade ,int dia, int mes, int ano){
        this.nome = nome;
        this.quantidade = quantidade;
        this.dia = dia;
        this.mes= mes;
        this.ano-= ano;
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

    public String getQuantidade() {
        return quantidade;
    }

    public int getMes() {
        return mes;
    }


}
