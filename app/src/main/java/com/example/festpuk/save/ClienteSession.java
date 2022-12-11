package com.example.festpuk.save;

import com.example.festpuk.entity.Entrada;
import com.example.festpuk.entity.Evento;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClienteSession {

    private static List<Evento> eventoList = new ArrayList<>();
    private static List<Evento> entradasList = new ArrayList<>();
    // private static List<Historial> historialLista = new ArrayList<>();


    public static List<Evento> getEventos() {
        return eventoList;
    }

    public static void setEventos(List<Evento> eventoList) {
        ClienteSession.eventoList = eventoList;
    }
    public static List<Evento> getEntradas() {
        return entradasList;
    }

    public static void setEntradas(List<Evento> entradasList) {
        ClienteSession.entradasList = entradasList;
    }

    public static void finish(){
        eventoList = new ArrayList<>();
        entradasList = new ArrayList<>();
     //   historialLista = new ArrayList<>();
    }

}