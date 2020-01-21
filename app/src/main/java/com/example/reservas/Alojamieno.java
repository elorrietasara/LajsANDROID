package com.example.reservas;

public class Alojamieno {

    String nombre;
    String telefono;
    String web;
    Double lat;
    Double lon;
    String tipo;
    String localidad;
    String descripcion;



    Integer capacidad;
    public Alojamieno() {


    }

    public Alojamieno(String nombre, String telefono,String web, Double lat, Double lon,String descripcion,String localidad, String tipo, Integer capacidad) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.web = web;
        this.lat=lat;
        this.lon=lon;
        this.descripcion=descripcion;
        this.localidad=localidad;
        this.tipo=tipo;
        this.capacidad=capacidad;

    }
    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



    public String getLocalidad() {
        return localidad;
    }
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
