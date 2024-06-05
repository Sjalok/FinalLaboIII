package com.TUP.Final_LaboIII.business;

import com.TUP.Final_LaboIII.model.Carrera;
import com.TUP.Final_LaboIII.model.dto.CarreraDto;

public interface CarreraService {
    Carrera crearCarrera(CarreraDto carreraDto);

    Carrera getCarrera(int codigocarrera);

    Carrera saveCarrera(int codigocarrera, Carrera carrera);

    Carrera borrarCarrera(int codigocarrera);
}
