package com.TUP.Final_LaboIII.model;

import java.util.List;

public class Alumno {
    private int id;
    private String nombre;
    private String apellido;
    private Long dni;
    private List<Asignatura> asignatura;
    private String carrera;

    public Alumno() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public List<Asignatura> getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(List<Asignatura> asignatura) {
        this.asignatura = asignatura;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
