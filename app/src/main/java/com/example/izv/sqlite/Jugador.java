package com.example.izv.sqlite;
import java.io.Serializable;

/**
 * Created by 2dam on 17/11/2014.
 */

//CLASE POJO
public class Jugador implements Serializable, Comparable<Jugador>{

    private long id;
    private String nombre;
    private String telefono;
    private String fnac;

    //1º constructor predeterminado
    //2ª constructor completo
    //3º Todos los get y set
    //4º equals, hashcode -> clave principal de la tabla
    //5º compareTo
    //6º toString

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

    @Override
    public int compareTo(Jugador jugador) {
        //Jugadores ->this, jugador

            if (this.nombre.compareTo(jugador.nombre) != 0) {
                return this.nombre.compareTo(jugador.nombre);
            } else {
                return this.fnac.compareTo(jugador.fnac);
            }

    }



    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fnac='" + fnac + '\'' +
                '}';
    }


}
