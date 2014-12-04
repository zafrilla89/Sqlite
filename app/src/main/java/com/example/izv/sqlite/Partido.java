package com.example.izv.sqlite;
import java.io.Serializable;

/**
 * Created by ZAFRA on 26/11/2014.
 */
public class Partido{

    private long id;
    private long id_jugador;
    private int valoracion;
    private String contrincante;

    public Partido() {
    }

    public Partido(int valoracion, String contrincante, long id_jugador) {
        this.id_jugador=id_jugador;
        this.valoracion = valoracion;
        this.contrincante=contrincante;
    }

    public Partido(String contrincante, long id, long id_jugador, String valoracion) {
        this.contrincante = contrincante;
        this.id = id;
        this.id_jugador = id_jugador;
        try {
            this.valoracion=Integer.parseInt(valoracion);
        }catch (NumberFormatException e){
            this.valoracion=0;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(long id_jugador) {
        this.id_jugador = id_jugador;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public String getContrincante() {
        return contrincante;
    }

    public void setContrincante(String contrincante) {
        this.contrincante = contrincante;
    }

    public boolean equals(Object o) {
        Partido p = (Partido) o;
        if (contrincante.compareTo(p.contrincante)==0 && id_jugador==p.id_jugador) {
            return true;
        }
        return false;
    }
}
