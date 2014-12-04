package com.example.izv.sqlite;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by 2dam on 21/11/2014.
 */
public class Adaptador extends CursorAdapter{
    private Cursor c;

    public Adaptador(Context context, Cursor c) {
        super(context, c, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup vg) {
        LayoutInflater i = LayoutInflater.from(vg.getContext());
        View v = i.inflate(R.layout.item, vg, false);
        return v;
    }

    @Override
        public void bindView(View v, Context co, Cursor c) {
            Jugador ju=new Jugador();
            ju= Gestorjugador.getRow(c);
            TextView tvnombre, tvtelefono, tvvaloracion, tvfecha;
            tvnombre=(TextView) v.findViewById(R.id.tvnombre);
            tvnombre.setText(ju.getNombre());
            tvtelefono=(TextView) v.findViewById(R.id.tvtelefono);
            tvtelefono.setText(ju.getTelefono());
            tvfecha=(TextView) v.findViewById(R.id.tvfecha);
            tvfecha.setText(ju.getFnac());

        }
}
