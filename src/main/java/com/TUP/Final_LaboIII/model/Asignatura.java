package com.TUP.Final_LaboIII.model;

public class Asignatura {
    private Materia materia;
    private EstadoAsignatura estado;
    private Integer nota;
    private Integer idAlumno;

    public Asignatura() {
    }

    public Asignatura(Materia materia, EstadoAsignatura estado, Integer nota, Integer idAlumno) {
        this.materia = materia;
        this.estado = estado;
        this.nota = nota;
        this.idAlumno = idAlumno;
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

    public EstadoAsignatura getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsignatura estado) {
        this.estado = estado;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getMateriaNombre() {
        return materia.getNombre();
    }
}
