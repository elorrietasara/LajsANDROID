package com.example.reservas;

public class Reserva {

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFe() {
        return fe;
    }

    public void setFe(String fe) {
        this.fe = fe;
    }

    public String getFs() {
        return fs;
    }

    public void setFs(String fs) {
        this.fs = fs;
    }

    public Reserva(String nombre, String fe, String fs) {
        this.nombre = nombre;
        this.fe = fe;
        this.fs = fs;
    }

     private  String nombre;
    private String fe;
    private String fs;
}
