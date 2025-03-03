package com.TUP.Final_LaboIII.model;

import java.util.List;

public class Profesor {
    private Long dni;
    private String nombre;
    private String apellido;
    private final String titulo;
    private List<Materia> materiasDictadas;

    public Profesor(Long dni, String nombre, String apellido, String titulo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.titulo = titulo;
    }

    public Profesor(Long dni, String nombre, String apellido, String titulo, List<Materia> materiasDictadas) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.titulo = titulo;
        this.materiasDictadas = materiasDictadas;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
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

    public String getTitulo() {
        return titulo;
    }

    public List<Materia> getMateriasDictadas() {
        return materiasDictadas;
    }

    public void agregarMateria(Materia materia){
        materiasDictadas.add(materia);
    }
}
