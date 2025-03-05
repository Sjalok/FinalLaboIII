package com.TUP.Final_LaboIII.business;

import com.TUP.Final_LaboIII.model.Carrera;
import com.TUP.Final_LaboIII.model.dto.CarreraDto;

import java.util.HashMap;
import java.util.List;

public interface CarreraService {
    String crearCarrera(CarreraDto carreraDto);

    Carrera getCarrera(int codigocarrera);

    HashMap<String, List<String>> getTodasLasCarreras();

    String borrarCarrera(int codigocarrera);

    Carrera agregarOEliminarMaterias(int codigocarrera, String nombremateria, String accion);

    boolean carreraExist(String nombrecarrera);
}
