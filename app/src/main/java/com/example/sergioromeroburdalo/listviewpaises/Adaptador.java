package com.example.sergioromeroburdalo.listviewpaises;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergioromeroburdalo on 24/4/18.
 */

public class Adaptador extends BaseAdapter {

    Context contexto;
    ArrayList<Ciudad> listaCiudades;
    int votacionesArrayOriginal[];
    //int votacionesArrayNeg[];
    Ciudad ci;
    //int votos=0;

    public Adaptador(Context contexto, ArrayList<Ciudad> listaCiudades) {
        this.contexto = contexto;
        this.listaCiudades = listaCiudades;
    }

    @Override
    public int getCount() {
        return listaCiudades.size();
    }

    @Override
    public Object getItem(int i) {
        return listaCiudades.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vista = view;
        final int pos = i;
        LayoutInflater inflate = LayoutInflater.from(contexto);
        vista = inflate.inflate(R.layout.itemlist, null);


        votacionesArrayOriginal = new int[listaCiudades.size()];
        for (int k=0;k<listaCiudades.size();k++)
        {
            votacionesArrayOriginal[k]=listaCiudades.get(k).getLikes();

        }

        TextView nombre = vista.findViewById(R.id.tvNombre);
        TextView pais = vista.findViewById(R.id.tvPais);
        TextView poblacion = vista.findViewById(R.id.tvPoblacion);
        TextView likes = vista.findViewById(R.id.tvLikes);

        final TextView like2 = likes;

        ImageView mm = vista.findViewById(R.id.imgTT);

        ImageView map = vista.findViewById(R.id.imgMap);

        ImageView up = vista.findViewById(R.id.btnUp);
        ImageView down = vista.findViewById(R.id.btnDown);

        nombre.setText(listaCiudades.get(i).getNombre());
        pais.setText(listaCiudades.get(i).getPais());
        poblacion.setText(listaCiudades.get(i).getPoblacion());
        likes.setText(Integer.valueOf(listaCiudades.get(i).getLikes()).toString());
        mm.setImageResource(listaCiudades.get(i).getImagen());

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

/* Explicacion de como pasar objeto a Otra activity

                    Intent intent = new Intent(Activity_Origen.this, Activity_Destino.class);
                    intent.putExtra("parametro", "string");
                    startActivity(intent);

 */
                Intent intent = new Intent(contexto, MapsActivity.class);
                intent.putExtra("objeto", listaCiudades.get(pos));
                contexto.startActivity(intent);
            }
        });


        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("valor", String.valueOf(votacionesArrayOriginal[pos] - listaCiudades.get(pos).getLikes()));
            if(votacionesArrayOriginal[pos]-listaCiudades.get(pos).getLikes()==1 ||
                    votacionesArrayOriginal[pos]-listaCiudades.get(pos).getLikes()==0) {
                listaCiudades.set(pos, listaCiudades.get(pos)).setLikes(listaCiudades.get(pos).getLikes() + 1);

                like2.setText(Integer.valueOf(listaCiudades.get(pos).getLikes()).toString());
            }

            }
        });


        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(votacionesArrayOriginal[pos]-listaCiudades.get(pos).getLikes()==-1 ||
                        votacionesArrayOriginal[pos]-listaCiudades.get(pos).getLikes()==0 && listaCiudades.get(pos).getLikes()>0) {
                listaCiudades.set(pos, listaCiudades.get(pos)).setLikes(listaCiudades.get(pos).getLikes()-1);

                like2.setText(Integer.valueOf(listaCiudades.get(pos).getLikes()).toString());

                }

            }
        });

        return vista;

    }
}
