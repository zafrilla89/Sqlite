package com.example.izv.sqlite;
import java.io.Serializable;

/**
 * Created by 2dam on 17/11/2014.
 */

//CLASE POJO
public class Jugador{

    private long id;
    private String nombre;
    private String telefono;
    private String fnac;

    public Jugador(){
        this("", 0, "", "");
    }

    public Jugador(String fnac, long id, String nombre, String telefono) {
        this.fnac = fnac;
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;

    }

    public Jugador(String nombre, String telefono, String fnac){
        this.fnac = fnac;
        this.nombre = nombre;
        this.telefono = telefono;


    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public String getFnac() {
        return fnac;
    }

    public void setFnac(String fnac) {
        this.fnac = fnac;
    }

}
