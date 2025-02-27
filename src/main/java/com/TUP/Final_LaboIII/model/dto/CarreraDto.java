package com.TUP.Final_LaboIII.model.dto;

public class CarreraDto {
    private String nombre;
    private int codigoCarrera;
    private int cantCuatrimestres;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigoCarrera() {
        return codigoCarrera;
    }

    public void setCodigoCarrera(int codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }

    public int getCantCuatrimestres() {
        return cantCuatrimestres;
    }

    public void setCantCuatrimestres(int cantCuatrimestres) {
        this.cantCuatrimestres = cantCuatrimestres;
    }
}
