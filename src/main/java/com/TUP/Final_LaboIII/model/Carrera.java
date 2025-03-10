package com.TUP.Final_LaboIII.model;

import java.util.ArrayList;
import java.util.List;

public class Carrera {
    private String nombre;
    private int codigoCarrera;
    private int departamento;
    private int cantCuatrimestres;
    private List<Materia> listaMaterias = new ArrayList<>();

    public Carrera(String nombre, int codigoCarrera, int departamento, int cantCuatrimestres) {
        this.nombre = nombre;
        this.codigoCarrera = codigoCarrera;
        this.departamento = departamento;
        this.cantCuatrimestres = cantCuatrimestres;
    }

    public Carrera(String nombre, int codigoCarrera, int departamento, int cantCuatrimestres, List<Materia> listaMaterias) {
        this.nombre = nombre;
        this.codigoCarrera = codigoCarrera;
        this.departamento = departamento;
        this.cantCuatrimestres = cantCuatrimestres;
        this.listaMaterias = listaMaterias;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantCuatrimestres() {
        return cantCuatrimestres;
    }

    public int getCodigoCarrera() {
        return codigoCarrera;
    }

    public List<Materia> getListaMaterias() {
        return listaMaterias;
    }

    public void setListaMaterias(List<Materia> listaMaterias) {
        this.listaMaterias = listaMaterias;
    }

    public int getDepartamento() {
        return departamento;
    }

    public void setDepartamento(int departamento) {
        this.departamento = departamento;
    }
}