package com.TUP.Final_LaboIII.model;

import java.util.ArrayList;
import java.util.List;

public class Materia {
    private String nombre;
    private List<Materia> correlatividades = new ArrayList<>();

    public Materia() {
    }

    public Materia(String nombre) {
        this.nombre = nombre;
    }

    public Materia(String nombre, List<Materia> correlatividades) {
        this.nombre = nombre;
        this.correlatividades = correlatividades;
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
