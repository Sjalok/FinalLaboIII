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

    public void sacarMateria(int idmateria){
        for (Materia materia: materiasDictadas) {
            if (materia.getMateriaId() == idmateria) {
                materiasDictadas.remove(materia);
            }
        }
    }
}
