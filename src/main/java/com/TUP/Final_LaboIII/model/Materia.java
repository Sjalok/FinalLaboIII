package com.TUP.Final_LaboIII.model;

import java.util.List;

public class Materia {
    private String nombre;
    private List<Materia> correlatividades;

    public Materia() {
    }

    public Materia(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Materia> getCorrelatividades() {
        return correlatividades;
    }

    public void setCorrelatividades(List<Materia> correlatividades) {
        this.correlatividades = correlatividades;
    }
}
