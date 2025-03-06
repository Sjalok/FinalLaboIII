package com.TUP.Final_LaboIII.model.dto;

public class AlumnoDto {
    private String nombre;
    private String apellido;
    private Long dni;
    private String carrera;

    public AlumnoDto(String nombre, String apellido, Long dni, String carrera) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.carrera = carrera;
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

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
