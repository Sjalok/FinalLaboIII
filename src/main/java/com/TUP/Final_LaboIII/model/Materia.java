package com.TUP.Final_LaboIII.model;

import java.util.List;

public class Materia {
    private int materiaId;
    private String nombre;
    private Profesor profesor;
    private List<Carrera> enQueCarreras;
    private List<Materia> correlatividades;

    public Materia() {
    }

    public Materia(int materiaId, String nombre) {
        this.materiaId = materiaId;
        this.nombre = nombre;
    }

    public List<Carrera> getEnQueCarreras() {
        return enQueCarreras;
    }

    public int getMateriaId() {
        return materiaId;
    }
    public void setMateriaId(int materiaId) {
        this.materiaId = materiaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
