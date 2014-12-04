package com.example.izv.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by ZAFRA on 02/12/2014.
 */
public class Adaptadorverpartido extends CursorAdapter {
    private Cursor c;

    public Adaptadorverpartido(Context context, Cursor c) {
        super(context, c, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup vg) {
        LayoutInflater i = LayoutInflater.from(vg.getContext());
        View v = i.inflate(R.layout.itempartido, vg, false);
        return v;
    }

    @Override
    public void bindView(View v, Context co, Cursor c) {
        Partidojugador pj=new Partidojugador();
        pj= Gestorverpartido.getRow(c);
        TextView tvnombre, tvtvaloracion, tvcontrincante;
        tvnombre=(TextView) v.findViewById(R.id.tvpnombre);
        tvnombre.setText(pj.getNombre());
        tvtvaloracion=(TextView) v.findViewById(R.id.tvpvaloracion);
        tvtvaloracion.setText(pj.getValoracion()+"");
        tvcontrincante=(TextView) v.findViewById(R.id.tvpcontrincante);
        tvcontrincante.setText(pj.getContrincante());

    }
}
