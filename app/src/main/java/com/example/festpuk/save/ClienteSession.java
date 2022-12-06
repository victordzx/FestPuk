package com.example.festpuk.save;

import com.example.festpuk.entity.Evento;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClienteSession {

    private static List<Evento> eventoList = new ArrayList<>();
   // private static List<Solicitud> solicitudes = new ArrayList<>();
   // private static List<Historial> historialLista = new ArrayList<>();

    public static void finish(){
        eventoList = new ArrayList<>();
     //   solicitudes = new ArrayList<>();
     //   historialLista = new ArrayList<>();
    }

}