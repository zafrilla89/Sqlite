package com.example.izv.sqlite;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ZAFRA on 02/12/2014.
 */
public class Gestorverpartido {
    private Ayudante abd;
    private SQLiteDatabase bd;

    public Gestorverpartido(Context c) {
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

    public static Partidojugador getRow(Cursor c) {
        Partidojugador partidojugador = new Partidojugador();
        partidojugador.setNombre(c.getString(1));
        partidojugador.setValoracion(c.getInt(2));
        partidojugador.setContrincante(c.getString(3));
        return partidojugador;
    }

    public Cursor getCursor() {
        String consulta="SELECT b."+Contrato.TablaPartido._ID+", "+Contrato.TablaJugador.NOMBRE+", "+
                Contrato.TablaPartido.VALORACION+", "+Contrato.TablaPartido.CONTRINCANTE+
                " FROM "+Contrato.TablaJugador.TABLA+" a INNER JOIN "+Contrato.TablaPartido.TABLA+" b "+
                " WHERE a."+Contrato.TablaJugador._ID+" = b."+Contrato.TablaPartido.IDJUGADOR;
        Cursor cursor =bd.rawQuery(consulta,null);
        return cursor;
    }

    public Cursor getCursor(long id) {
        String consulta="SELECT b."+Contrato.TablaPartido._ID+", "+Contrato.TablaJugador.NOMBRE+", "+
                Contrato.TablaPartido.VALORACION+", "+Contrato.TablaPartido.CONTRINCANTE+
                " FROM "+Contrato.TablaJugador.TABLA+" a INNER JOIN "+Contrato.TablaPartido.TABLA+" b "+
                " ON a."+Contrato.TablaJugador._ID+" = b."+Contrato.TablaPartido.IDJUGADOR +" WHERE a."+
                Contrato.TablaJugador._ID+"="+id;
        Cursor cursor =bd.rawQuery(consulta,null);
        return cursor;
    }




}
