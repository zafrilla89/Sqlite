package com.example.izv.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class Verpartido extends Activity {
    private Gestorverpartido gvp;
    private ListView lv;
    private Adaptadorverpartido a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verpartido);
        gvp= new Gestorverpartido(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        gvp.open();
        Cursor c= gvp.getCursor();
        lv=(ListView)findViewById(R.id.lvpartidos);
        a=new Adaptadorverpartido(this, c);
        lv.setAdapter(a);

    }

    public void onPause() {
        super.onPause();
        gvp.close();
    }
}
