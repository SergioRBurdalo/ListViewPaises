package com.example.sergioromeroburdalo.listviewpaises;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listCiudades;
    ArrayList<Ciudad> lista;
    Ciudad cd;
    ImageView map;
    List<String> arrayLikes;
    List<String> aux ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCiudades = findViewById(R.id.lvPaises);
        map = findViewById(R.id.imgMap);

        lista = new ArrayList<Ciudad>();

        lista.add(new Ciudad("Bruselas", "Belgica", "177 307",50.8467, 4.3547, 23, R.drawable.bruselas));
        lista.add(new Ciudad("Budapest", "Hungría", "1 752 704", 47.498333, 19.040833, 42 ,R.drawable.budapest));
        lista.add(new Ciudad("Dublin", "Irlanda", "527 612", 53.3425, -6.265833, 17, R.drawable.dublin));
        lista.add(new Ciudad("Florencia", "Italia", "382 258", 43.771389, 11.254167, 63, R.drawable.florencia));
        lista.add(new Ciudad("Paris", "Francia", "10 516 110", 48.856578, 2.351828, 89, R.drawable.paris));
        lista.add(new Ciudad("Praga", "Chequia", "1 280 508", 50.088611, 14.421389, 61, R.drawable.praga));
        lista.add(new Ciudad("Sevilla", "España", "689 434", 37.383333, -5.983333, 54, R.drawable.sevilla));
        lista.add(new Ciudad("Viena", "Austria", "1 840 573", 48.20833, 16.373064, 49, R.drawable.viena));

        SharedPreferences preferencias = getSharedPreferences("ficheroLikes", MODE_PRIVATE);
        String cadena  = preferencias.getString("cadenaLikes","0,0,0,0,0,0,0,0");

        arrayLikes = convertToArray(cadena);
        for (int i=0;i<lista.size();i++)
        {
            lista.get(i).setLikes(Integer.parseInt(arrayLikes.get(i)));
        }

        //super.onDestroy objeto sharedpreference

        Adaptador miAdaptador = new Adaptador(getApplicationContext(), lista);

        listCiudades.setAdapter(miAdaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_uno, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.item_like)
        {
            ordenarPorLike(lista);
            return true;
        }

        if(id==R.id.item_nombre)
        {
            ordenarPorNombre(lista);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    public  void ordenarPorLike(ArrayList<Ciudad> likes){

        Collections.sort(likes, new Comparator<Ciudad>() {
            @Override
            public int compare(Ciudad c1, Ciudad c2) {
                return Integer.valueOf(c1.getLikes()).compareTo(Integer.valueOf(c2.getLikes()));            }
        });


        Adaptador miAdaptador = new Adaptador(getApplicationContext(), likes);

        listCiudades.setAdapter(miAdaptador);

            }

    public  void ordenarPorNombre(ArrayList<Ciudad> likes){

        Collections.sort(likes, new Comparator<Ciudad>() {
            @Override
            public int compare(Ciudad c1, Ciudad c2) {
                return c1.getNombre().compareTo(c2.getNombre());            }
        });


        Adaptador miAdaptador = new Adaptador(getApplicationContext(), likes);

        listCiudades.setAdapter(miAdaptador);

    }

    public List<String> convertToArray(String cadenita)
    {
        String [] cadenas = cadenita.split(",");
        List<String> list = Arrays.asList(cadenas);
        return list;
    }

    public String converToString(List<String> cadenita)
    {
        String cadenaBuena = null;

        for (int i = 0; i < lista.size(); i++) {
            if (cadenaBuena == null) {
                cadenaBuena= cadenita.get(i);
            }
            else {
                cadenaBuena = cadenaBuena + "," + cadenita.get(i);
            }
        }
        return cadenaBuena;
    }

    @Override
    protected void onDestroy() {





        for (int i = 0; i<lista.size();i++){

            //aux.add(String.valueOf(cd.getLikes()));
            //arrayLikes.get(i); //IGUALAMOS AL VALOR QUE TENGA EL TV EN ESE MOMENTO PARA LLENAR DE VALORES EL ARRAY
        }

        SharedPreferences preferencias = getSharedPreferences("ficheroLikes", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();

        String cadenaBuena = converToString(arrayLikes);

        Log.d("valor", cadenaBuena);

        editor.putString("cadenaLikes", cadenaBuena);
        editor.commit();

        super.onDestroy();
    }


}
