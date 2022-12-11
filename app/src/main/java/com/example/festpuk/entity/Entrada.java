package com.example.festpuk.entity;

public class Entrada {

    private String key;
    private String tipoentrada;
    private int numeroacomprar;

    public Entrada(){}

    public Entrada(String tipoentrada, int numeroacomprar){
        this.tipoentrada = tipoentrada;
        this.numeroacomprar = numeroacomprar;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTipoentrada() {
        return tipoentrada;
    }

    public void setTipoentrada(String tipoentrada) {
        this.tipoentrada = tipoentrada;
    }

    public int getNumeroacomprar() {
        return numeroacomprar;
    }

    public void Numeroacomprar(int numeroacomprar) {
        this.numeroacomprar = numeroacomprar;
    }

}
