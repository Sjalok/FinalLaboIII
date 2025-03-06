package com.TUP.Final_LaboIII.model.dto;

public class CarreraDto {
    private String nombre;
    private int departamento;
    private int codigoCarrera;
    private int cantCuatrimestres;

    public CarreraDto(String nombre, int departamento, int codigoCarrera, int cantCuatrimestres) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.codigoCarrera = codigoCarrera;
        this.cantCuatrimestres = cantCuatrimestres;
    }

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

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }
}
