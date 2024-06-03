package com.TUP.Final_LaboIII.persistence;

import com.TUP.Final_LaboIII.model.Carrera;

public interface CarreraDao {
    Carrera newCarrera(Carrera carrera);

    Carrera deleteCarrera(int codigocarrera);

    Carrera loadCarrera(int codigocarrera);

    Carrera findByCode(int codigocarrera);

    Carrera saveCarrera(int codigocarrera, Carrera carrera);
}
