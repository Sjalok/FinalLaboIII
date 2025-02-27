package com.TUP.Final_LaboIII.business;

import com.TUP.Final_LaboIII.model.Carrera;
import com.TUP.Final_LaboIII.model.dto.CarreraDto;

import java.util.HashMap;
import java.util.List;

public interface CarreraService {
    Carrera crearCarrera(CarreraDto carreraDto);

    Carrera getCarrera(int codigocarrera);

    Carrera saveCarrera(int codigocarrera, Carrera carrera);

    HashMap<String, List<String>> getTodasLasCarreras();

    Carrera borrarCarrera(int codigocarrera);
}
