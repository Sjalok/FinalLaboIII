package com.TUP.Final_LaboIII.model.dto;

public class MateriaDto {
    private String nombre;

    public MateriaDto() {
    }

    public MateriaDto(String nombre) {
        this.nombre = nombre;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
