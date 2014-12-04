package com.example.izv.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class Alta_partido extends Activity {
    private long id;
    private EditText etvaloracion, etcontrincante;
    private ArrayList<Partido> datos;
    private Gestorpartido gv;
    private Gestorverpartido gvp;
    private ListView lv;
    private Adaptadorverpartido a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_partido);
        gvp=new Gestorverpartido(this);
        gv=new Gestorpartido(this);
        etvaloracion = (EditText) findViewById(R.id.etvaloracion);
        etcontrincante = (EditText) findViewById(R.id.etcontrincante);
        Bundle b = getIntent().getExtras();
        if (b != null) {
            id = b.getLong("id");
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        gvp.open();
        Cursor c = gvp.getCursor(id);
        lv = (ListView) findViewById(R.id.lvpartido);
        a = new Adaptadorverpartido(this, c);
        lv.setAdapter(a);

    }

    @Override
    public void onPause() {
        super.onPause();
        gvp.close();
    }

    public void alta(View view){
        gv.open();
        String valoracion, contrincante;
        valoracion = etvaloracion.getText().toString();
        contrincante = etcontrincante.getText().toString();
        if (contrincante.compareTo("")!=0 && valoracion.compareTo("")!=0) {
            int va = Integer.parseInt(valoracion);
                Partido partido = new Partido(va, contrincante, id);
                datos = (ArrayList<Partido>) gv.select();
                boolean comprobar = comprueba(partido);
                if (comprobar == true) {
                    gv.insert(partido);
                    a.getCursor().close();
                    Cursor c = gvp.getCursor(id);
                    a.changeCursor(c);
                } else {
                    Toast.makeText(this, "Ese partido ya existe", Toast.LENGTH_SHORT).show();
                }
        }else{
            Toast.makeText(this, "Tienes que rellenar los dos campos", Toast.LENGTH_SHORT).show();
        }
        gv.close();
    }

    public void volver(View view){
        Intent i =new Intent(this,Principal.class);
        startActivity(i);
        finish();
    }

    public boolean comprueba(Partido p2){
        Partido p;
        for (int i=0;i<datos.size();i++){
            p=datos.get(i);
            if(p.getContrincante().compareTo(p2.getContrincante())==0 && p.getId_jugador()==p2.getId_jugador()){
                return false;
            }
        }
        return true;
    }
}


