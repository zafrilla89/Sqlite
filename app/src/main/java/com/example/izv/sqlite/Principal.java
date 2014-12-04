package com.example.izv.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Principal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void altas(View view){
        Intent i = new Intent(this,Alta_jugador.class);
        startActivity(i);
    }

    public void verpartidos(View view){
        Intent i = new Intent(this,Verpartido.class);
        startActivity(i);
    }
}
