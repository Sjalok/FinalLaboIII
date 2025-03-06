package com.TUP.Final_LaboIII.model.dto;

public class ProfesorDto {
    private String nombre;
    private String apellido;
    private Long dni;
    private String titulo;

    public ProfesorDto(String nombre, String apellido, Long dni, String titulo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.titulo = titulo;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
