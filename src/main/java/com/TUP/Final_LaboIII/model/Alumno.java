package com.TUP.Final_LaboIII.model;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
    private int id;
    private String nombre;
    private String apellido;
    private Long dni;
    private List<Integer> asignaturas;
    private String carrera;

    public Alumno() {
    }

    public Alumno(int id, String nombre, String apellido, Long dni, List<Integer> asignaturas, String carrera) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.asignaturas = asignaturas;
        this.carrera = carrera;
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

    public List<Integer> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Integer> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
