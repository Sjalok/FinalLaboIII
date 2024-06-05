package com.TUP.Final_LaboIII.model;

import java.util.List;

public class Carrera {
    private String nombre;
    private int codigoCarrera;
    private String departamento;
    private int cantCuatrimestres;
    private List<Materia> listaMaterias;

    public Carrera(String nombre, int codigoCarrera, String departamento, int cantCuatrimestres) {
        this.nombre = nombre;
        this.codigoCarrera = codigoCarrera;
        this.departamento = departamento;
        this.cantCuatrimestres = cantCuatrimestres;
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

    public void agregarMateria(Materia materia){
        listaMaterias.add(materia);
    }

    public void sacarMateria(int idMateria){
        for (Materia materia: listaMaterias) {
            if (materia.getMateriaId() == idMateria) {
                listaMaterias.remove(materia);
            }
        }
    }
}
