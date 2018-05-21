package com.example.sergioromeroburdalo.listviewpaises;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by sergioromeroburdalo on 24/4/18.
 */

public class Ciudad implements Serializable {
    private String nombre;
    private String pais;
    private String poblacion;
    private double latitud;
    private double longitud;
    private int likes;
    private int imagen;

    public Ciudad()
    {

    }

    public Ciudad(String nombre, String pais, String poblacion, double latitud, double longitud, int likes, int imagen) {
        this.nombre = nombre;
        this.pais = pais;
        this.poblacion = poblacion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.likes = likes;
        this.imagen = imagen;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
