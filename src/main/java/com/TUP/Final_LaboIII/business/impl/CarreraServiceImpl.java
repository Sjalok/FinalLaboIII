package com.TUP.Final_LaboIII.business.impl;

import com.TUP.Final_LaboIII.business.CarreraService;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.model.Carrera;
import com.TUP.Final_LaboIII.model.dto.CarreraDto;
import com.TUP.Final_LaboIII.persistence.CarreraDao;
import com.TUP.Final_LaboIII.persistence.impl.CarreraDaoImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CarreraServiceImpl implements CarreraService {

    private static final CarreraDao carreraDao = new CarreraDaoImpl();
    @Override
    public Carrera crearCarrera(CarreraDto carreraDto) {
        return null;
    }

    @Override
    public Carrera getCarrera(int codigocarrera) {
        Carrera c = carreraDao.loadCarrera(codigocarrera);

        if (c == null) {
            throw new NotFoundException("No existe una carrera con ese codigo.");
        }

        return c;
    }

    @Override
    public Carrera saveCarrera(int codigocarrera, Carrera carrera) {
        return null;
    }

    @Override
    public HashMap<String, List<String>> getTodasLasCarreras() {
        return carreraDao.mostrarTodasLasCarreras();
    }

    @Override
    public Carrera borrarCarrera(int codigocarrera) {
        if (carreraDao.findByCode(codigocarrera)) {
            return carreraDao.deleteCarrera(codigocarrera);
        }
        throw new NotFoundException("No existe una carrera con ese codigo.");
    }
}
