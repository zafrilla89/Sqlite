package com.example.izv.sqlite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZAFRA on 26/11/2014.
 */
public class Gestorpartido {

    private Ayudante abd;
    private SQLiteDatabase bd;

    public Gestorpartido(Context c) {
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

    public long insert(Partido pa) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaPartido.IDJUGADOR, pa.getId_jugador());
        valores.put(Contrato.TablaPartido.CONTRINCANTE, pa.getContrincante());
        valores.put(Contrato.TablaPartido.VALORACION, pa.getValoracion());
        long id = bd.insert(Contrato.TablaPartido.TABLA, null, valores);
        //id -> codigo autonumerico
        return id;
    }

    public int delete(Partido pa) {
        String condicion = Contrato.TablaPartido._ID + " = ?";
        String[] argumentos = { pa.getId() + "" };
        int cuenta = bd.delete(
                Contrato.TablaJugador.TABLA, condicion,argumentos);
        return cuenta;
    }

    public int update(Partido pa) {
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaPartido.IDJUGADOR, pa.getId_jugador());
        valores.put(Contrato.TablaPartido.CONTRINCANTE,pa.getContrincante());
        valores.put(Contrato.TablaPartido.VALORACION, pa.getValoracion());
        String condicion = Contrato.TablaPartido._ID + " = ?";
        String[] argumentos = { pa.getId() + "" };
        int cuenta = bd.update(Contrato.TablaPartido.TABLA, valores,
                condicion, argumentos);
        return cuenta;
    }

    public List<Partido> select() {
        return select(null,null,null);
    }

    public List<Partido> select(String condicion, String[] parametros, String orderby) {
        List<Partido> alj = new ArrayList<Partido>();
        Cursor cursor = bd.query(Contrato.TablaPartido.TABLA, null,
                condicion, parametros, null, null, orderby); //Select from Jugador where condicion orederBy orederby;
        cursor.moveToFirst();
        Partido pa;
        while (!cursor.isAfterLast()) {
            pa = getRow(cursor);
            alj.add(pa);
            cursor.moveToNext();
        }
        cursor.close();
        return alj;
    }

    public static Partido getRow(Cursor c) {
        Partido pa = new Partido();
        pa.setId(c.getLong(0));
        pa.setId_jugador(c.getLong(1));
        pa.setValoracion(c.getInt(2));
        pa.setContrincante(c.getString(3));
        return pa;
    }

    public Partido getRow(long id){
        List<Partido> alj= select(Contrato.TablaPartido._ID + " = ?",new String[]{ id+"" },null );
        if (alj.isEmpty()) {  //isEmpty es si esta vacio
            return alj.get(0);
        }
        return null;
    }

    public Cursor getCursor(String condicion, String[] parametros, String orderby) {
        Cursor cursor = bd.query(
                Contrato.TablaPartido.TABLA, null, condicion, parametros, null, null,
                orderby);
        return cursor;
    }

    public Cursor getCursor() {
        Cursor cursor = bd.query(
                Contrato.TablaPartido.TABLA, null, null, null, null, null,
                null);
        return cursor;
    }

}
