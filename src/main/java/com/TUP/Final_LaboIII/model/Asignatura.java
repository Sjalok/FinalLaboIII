package com.TUP.Final_LaboIII.model;

public class Asignatura {
    private Materia materia;
    private Integer nota;

    public Asignatura() {
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}
