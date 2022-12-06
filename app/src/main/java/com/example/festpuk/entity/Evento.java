package com.example.festpuk.entity;

import java.io.Serializable;
import java.util.Date;

public class Evento implements Serializable {

    private String key;
    private String titulo;
    private String organizador;
    private String descripcion;
    private String ubicacion;
    private String contacto;
    private String fecha;
    private double precio;
    private int stock;
    private String fotourl;

    public Evento(){
    }

    public Evento(String titulo, String fecha, String organizador, String descripcion, String ubicacion, String contacto, double precio, int stock, String fotourl){
        this.titulo = titulo;
        this.fecha = fecha;
        this.organizador = organizador;
        this.descripcion = descripcion;
        this.contacto = contacto;
        this.precio = precio;
        this.stock = stock;
        this.fotourl = fotourl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getFotourl() {
        return fotourl;
    }

    public void setFotourl(String fotourl) {
        this.fotourl = fotourl;
    }
}
