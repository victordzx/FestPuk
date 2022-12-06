package com.example.festpuk.entity;

import java.util.Date;

public class Cuenta {

    private String rol;
    private String correo;
    private String nombre;
    private String fechanac;
    private String gender;
    private int dni;

    public Cuenta(){
    }

    public Cuenta(String rol, String correo, String nombre, String fechanac, String gender, int dni){
        this.rol = rol;
        this.correo = correo;
        this.nombre = nombre;
        this.fechanac = fechanac;
        this.gender = gender;
        this.dni = dni;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

}
