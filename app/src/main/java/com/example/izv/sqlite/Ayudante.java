package com.example.izv.sqlite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 2dam on 14/11/2014.
 */
public class Ayudante extends SQLiteOpenHelper {

        public static final String DATABASE_NAME = "futbol.db";
        public static final int DATABASE_VERSION = 2;

        public Ayudante(Context context) {
            super(context, DATABASE_NAME, null,DATABASE_VERSION);
        }

    @Override
          public void onCreate(SQLiteDatabase db) {
                String sql;
                sql="create table "+Contrato.TablaJugador.TABLA+
                        " ("+ Contrato.TablaJugador._ID+
                        " integer primary key autoincrement, "+
                        Contrato.TablaJugador.NOMBRE+" text, "+
                        Contrato.TablaJugador.TELEFONO+" text, "+
                        Contrato.TablaJugador.FNAC+" text)";
                db.execSQL(sql);
                sql="create table "+Contrato.TablaPartido.TABLA+
                        " ("+ Contrato.TablaPartido._ID+
                        " integer PRIMARY KEY autoincrement, "+
                        Contrato.TablaPartido.IDJUGADOR+" integer, "+
                        Contrato.TablaPartido.VALORACION+" integer, "+
                        Contrato.TablaPartido.CONTRINCANTE+" text, "+
                        "unique ("+Contrato.TablaPartido.IDJUGADOR+" ,"+Contrato.TablaPartido.CONTRINCANTE+"))";
                db.execSQL(sql);
    }

    @Override
         public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion) {
            if (oldVersion<2) {
                String sql = "CREATE TABLE Juga (id INTEGER, nombre TEXT, telefono TEXT, valoracion INTEGER,  fnac TEXT)";
                db.execSQL(sql);
                sql="INSERT INTO Juga  SELECT * FROM jugador";
                db.execSQL(sql);
                sql="drop table if exists "+ Contrato.TablaJugador.TABLA;
                db.execSQL(sql);
                onCreate(db);
                sql="INSERT INTO "+Contrato.TablaJugador.TABLA+" ("+Contrato.TablaJugador.NOMBRE +" , "+
                        Contrato.TablaJugador.TELEFONO+" , "+Contrato.TablaJugador.FNAC +") SELECT nombre, telefono, fnac FROM Juga";
                db.execSQL(sql);
                sql="INSERT INTO "+Contrato.TablaPartido.TABLA+" ("+Contrato.TablaPartido.VALORACION+" , "
                        +Contrato.TablaPartido.IDJUGADOR+
                        " ) SELECT valoracion, "+Contrato.TablaJugador._ID+" FROM Juga j INNER JOIN "+
                        Contrato.TablaJugador.TABLA+" ju WHERE j.nombre=ju."+Contrato.TablaJugador.NOMBRE+
                        " AND j.telefono=ju."+Contrato.TablaJugador.TELEFONO+
                        " AND j.fnac=ju."+Contrato.TablaJugador.FNAC;
                db.execSQL(sql);
                sql="UPDATE "+Contrato.TablaPartido.TABLA+" SET "+Contrato.TablaPartido.CONTRINCANTE+"='Media anterior'";
                db.execSQL(sql);
                sql="drop table Juga";
                db.execSQL(sql);
            }
        }





}
