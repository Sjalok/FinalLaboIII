package com.TUP.Final_LaboIII.business.impl;

import com.TUP.Final_LaboIII.business.CarreraService;
import com.TUP.Final_LaboIII.model.Carrera;
import com.TUP.Final_LaboIII.model.dto.CarreraDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CarreraServiceImpl implements CarreraService {
    @Override
    public Carrera crearCarrera(CarreraDto carreraDto) {
        return null;
    }

    @Override
    public Carrera getCarrera(int codigocarrera) {
        return null;
    }

    @Override
    public Carrera saveCarrera(int codigocarrera, Carrera carrera) {
        return null;
    }

    @Override
    public boolean findCarreraByName(String nombrecarrera) {
        return false;
    }

    @Override
    public HashMap<String, List<String>[]> getTodasLasCarreras() {
        return null;
    }

    @Override
    public Carrera borrarCarrera(int codigocarrera) {
        return null;
    }
}
