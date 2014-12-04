package com.example.izv.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class Alta_jugador extends Activity {
    private Gestorjugador gj;
    private EditText etnombre, etfecha, ettelefono;
    private ListView lv;
    private Adaptador a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_jugador);
        gj = new Gestorjugador(this);
        etnombre = (EditText) findViewById(R.id.etnombre);
        etfecha = (EditText) findViewById(R.id.etfecha);
        ettelefono = (EditText) findViewById(R.id.ettelefono);
    }

    @Override
    public void onResume() {
        super.onResume();
        gj.open();
        Cursor c = gj.getCursor();
        lv = (ListView) findViewById(R.id.lw);
        a = new Adaptador(this, c);
        lv.setAdapter(a);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Cursor cursor = (Cursor) lv.getItemAtPosition(pos);
                Jugador j = Gestorjugador.getRow(cursor);
                long id = j.getId();
                Intent i = new Intent(Alta_jugador.this, Alta_partido.class);
                i.putExtra("id", id);
                startActivity(i);
                return false;
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        gj.close();
    }

    public void volver(View view) {
        Intent i =new Intent(this,Principal.class);
        startActivity(i);
        finish();
    }

    public void alta(View view) {
        String nombre, fecha, telefono;
        nombre = etnombre.getText().toString();
        fecha = etfecha.getText().toString();
        telefono = ettelefono.getText().toString();
        Jugador ju = new Jugador(nombre, telefono, fecha);
        long id = gj.insert(ju);
        a.getCursor().close();
        Cursor c = gj.getCursor();
        a.changeCursor(c);
    }
}