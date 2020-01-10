package com.example.reservas;

public class Alojamieno {

    String nombre;
    String telefono;
    String web;
    Double lat;
    Double lon;

    public Alojamieno(String nombre, String telefono,String web, Double lat, Double lon) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.web = web;
        this.lat=lat;
        this.lon=lon;

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
