package com.example.izv.sqlite;
/**
 * Created by ZAFRA on 02/12/2014.
 */
public class Partidojugador {
    private String nombre;
    private int valoracion;
    private String contrincante;

    public Partidojugador() {
    }

    public Partidojugador(String contrincante, String nombre, int valoracion) {
        this.contrincante = contrincante;
        this.nombre = nombre;
        this.valoracion = valoracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
}
