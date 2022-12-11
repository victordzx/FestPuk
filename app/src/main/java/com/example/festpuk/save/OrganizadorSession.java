package com.example.festpuk.save;

import com.example.festpuk.entity.Evento;

import java.util.ArrayList;
import java.util.List;

public class OrganizadorSession {
    private static List<Evento> eventoList = new ArrayList<>();
    // private static List<Solicitud> solicitudes = new ArrayList<>();
    // private static List<Historial> historialLista = new ArrayList<>();

    public static List<Evento> getEventos() {
        return eventoList;
    }

    public static void setEventos(List<Evento> eventoList) {
        OrganizadorSession.eventoList = eventoList;
    }

    public static void finish(){
        eventoList = new ArrayList<>();
        //   solicitudes = new ArrayList<>();
        //   historialLista = new ArrayList<>();
    }
}
