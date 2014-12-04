package com.example.izv.sqlite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 2dam on 17/11/2014.
 */
public class Gestorjugador {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public Gestorjugador(Context c) {
        abd = new Ayudante(c);
    }

    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void openRead() {
        bd = abd.getReadableDatabase();
    }

    public void close() {
        abd.close();
    }

    public long insert(Jugador ju) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaJugador.FNAC, ju.getFnac());
        valores.put(Contrato.TablaJugador.NOMBRE, ju.getNombre());
        valores.put(Contrato.TablaJugador.TELEFONO, ju.getTelefono());
        long id = bd.insert(Contrato.TablaJugador.TABLA, null, valores);
        //id -> codigo autonumerico
        return id;
    }

    public int delete(Jugador ju) {
        String condicion = Contrato.TablaJugador._ID + " = ?";
        String[] argumentos = { ju.getId() + "" };
        int cuenta = bd.delete(
                Contrato.TablaJugador.TABLA, condicion,argumentos);
        return cuenta;
    }


    /*public int deletev2(){
        String condicion = Contrato.TablaJugador.NOMBRE + " = ? or "+Contrato.TablaJugador.VALORACION+" < ?";
        String nombre="pepe";
        int valoracion=6;
        String[] argumentos = { nombre, valoracion+"" };
        int cuenta = bd.delete(
                Contrato.TablaJugador.TABLA, condicion,argumentos);
        nombre="juan";
        valoracion=3;
        argumentos[0]=nombre;
        argumentos[1]=valoracion+"";
        cuenta = bd.delete(
                Contrato.TablaJugador.TABLA, condicion,argumentos);
        return cuenta;
    }*/

    public int update(Jugador ju) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaJugador.FNAC, ju.getFnac());
        valores.put(Contrato.TablaJugador.NOMBRE, ju.getNombre());
        valores.put(Contrato.TablaJugador.TELEFONO, ju.getTelefono());
        String condicion = Contrato.TablaJugador._ID + " = ?";
        String[] argumentos = { ju.getId() + "" };
        int cuenta = bd.update(Contrato.TablaJugador.TABLA, valores,
                condicion, argumentos);
        return cuenta;
    }

    public List<Jugador> select() {
        return select(null,null,null);
    }

    public List<Jugador> select(String condicion, String[] parametros, String orderby) {
        List<Jugador> alj = new ArrayList<Jugador>();
        Cursor cursor = bd.query(Contrato.TablaJugador.TABLA, null,
                condicion, parametros, null, null, orderby); //Select from Jugador where condicion orederBy orederby;
        cursor.moveToFirst();
        //String[] columnas={"nombre", "valoracion"};
        //String[] parametrosCondicion ={"pepe, 6"};
        //String orderby = Contrato.TablaJugador.NOMBRE + " ASC";
        //bd.query("Tabla", columnas, condicion ("nombre = ? AND valoracion = ?"), parametrosCondicion, groupBy (null), having (null), orderby);
        //Select nombre, valoracion
        //    from tabla where nombre=? AND valoracion=?
       //     orderby valoracion;
        Jugador ju;
        while (!cursor.isAfterLast()) {
            ju = getRow(cursor);
            alj.add(ju);
            cursor.moveToNext();
        }
        cursor.close();
        return alj;
    }

    public static Jugador getRow(Cursor c) {
        Jugador ju = new Jugador();
        ju.setId(c.getLong(0));
        ju.setNombre(c.getString(1));
        ju.setTelefono(c.getString(2));
        ju.setFnac(c.getString(3));
        return ju;
    }

    //public Jugador getRow(long id) {
        //String[] proyeccion = { Contrato.TablaJugador._ID,
          //      Contrato.TablaJugador.NOMBRE,
          //      Contrato.TablaJugador.TELEFONO,
          //      Contrato.TablaJugador.TELEFONO,
          //      Contrato.TablaJugador.VALORACION,
         //       Contrato.TablaJugador.FNAC};
        //String where = Contrato.TablaJugador._ID + " = ?";
        //String[] parametros = new String[] { id+"" };
        //String groupby = null;
        //String having = null;
        //String orderby = Contrato.TablaJugador.NOMBRE + " ASC";
        //Cursor c = bd.query(Contrato.TablaJugador.TABLA, proyeccion,
       //         where, parametros, groupby, having, orderby);
       // c.moveToFirst();
       // Jugador ju = getRow(c);
      //  c.close();
     //   return ju;
    //}

    public Jugador getRow(long id){
        List<Jugador> alj= select(Contrato.TablaJugador._ID + " = ?",new String[]{ id+"" },null );
        if (alj.isEmpty()) {  //isEmpty es si esta vacio
            return alj.get(0);
        }
        return null;
    }

    public Cursor getCursor(String condicion, String[] parametros, String orderby) {
        Cursor cursor = bd.query(
                Contrato.TablaJugador.TABLA, null, condicion, parametros, null, null,
                orderby);
        return cursor;
    }

    public Cursor getCursor() {
        Cursor cursor = bd.query(
                Contrato.TablaJugador.TABLA, null, null, null, null, null,
                null);
        return cursor;
    }

}
