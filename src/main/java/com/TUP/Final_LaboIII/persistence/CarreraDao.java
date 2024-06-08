package com.TUP.Final_LaboIII.persistence;

import com.TUP.Final_LaboIII.model.Carrera;

import java.util.HashMap;
import java.util.List;

public interface CarreraDao {
    Carrera newCarrera(Carrera carrera);

    Carrera deleteCarrera(int codigocarrera);

    Carrera loadCarrera(int codigocarrera);

    boolean findByCode(int codigocarrera);

    boolean findByName(String nombrecarrera);

    HashMap<String, List<String>> mostrarTodasLasCarreras();

    Carrera saveCarrera(int codigocarrera, Carrera carrera);
}
