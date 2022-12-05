package com.example.festpuk.save;

public class ReadSession {
    String rol="";
    String correo="";

    public ReadSession(){
        this.rol ="";
        this.correo ="";
    }

    public ReadSession(String rol, String correo) {
        this.rol = rol;
        this.correo = correo;
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
}