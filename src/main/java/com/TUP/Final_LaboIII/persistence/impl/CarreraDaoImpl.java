package com.TUP.Final_LaboIII.persistence.impl;

import com.TUP.Final_LaboIII.model.Carrera;
import com.TUP.Final_LaboIII.persistence.CarreraDao;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.business.exception.WrongCodeException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CarreraDaoImpl implements CarreraDao {

    private static Map<Integer, Carrera> repositorioCarreras = new HashMap<>();

    @Override
    public Carrera newCarrera(Carrera carrera) {
        return repositorioCarreras.put(carrera.getCodigoCarrera() ,carrera);
    }

    @Override
    public Carrera deleteCarrera(int codigocarrera) {
        if (!findByCode(codigocarrera)) {
            throw new NotFoundException("No se encontro una carrera con el id " + codigocarrera);
        }
        for (Carrera carrera: repositorioCarreras.values()) {
            if (codigocarrera == carrera.getCodigoCarrera()) {
                return repositorioCarreras.remove(codigocarrera);
            }
        }
        return null;
    }

    @Override
    public Carrera loadCarrera(int codigocarrera) {
        if (!findByCode(codigocarrera)) {
            throw new NotFoundException("No se encontro una carrera con el id " + codigocarrera);
        }
        for (Carrera carrera: repositorioCarreras.values()) {
            if (codigocarrera == carrera.getCodigoCarrera()) {
                return carrera;
            }
        }
        return null;
    }

    @Override
    public boolean findByCode(int codigocarrera) {
        for (Carrera carrera: repositorioCarreras.values()) {
            if (codigocarrera == carrera.getCodigoCarrera()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean findByName(String nombrecarrera) {
        for (Carrera carrera: repositorioCarreras.values()) {
            if (nombrecarrera.equals(carrera.getNombre())) {
                return true;
            }
        }
        throw new NotFoundException("No existe una carrera con ese nombre.");
    }

    @Override
    public HashMap<String, List<String>> mostrarTodasLasCarreras() {
        HashMap<String, List<String>> todasLasCarreras = new HashMap<>();
        List<String> carreras = new ArrayList<>();

        for (Carrera carrera: repositorioCarreras.values()) {
            carreras.add(carrera.getNombre());
        }

        todasLasCarreras.put("Carreras:", carreras);
        return todasLasCarreras;
    }


    @Override
    public Carrera saveCarrera(int codigocarrera, Carrera carrera) {
        if (!findByCode(codigocarrera)) {
            throw new NotFoundException("No se encontro una carrera con el id " + codigocarrera);
        } else if (!repositorioCarreras.get(codigocarrera).getNombre().equals(carrera.getNombre())) {
            throw new NotFoundException("No se encontro una carrera con el id " + codigocarrera);
        }
        for (Carrera carreras: repositorioCarreras.values()) {
            if (codigocarrera == carrera.getCodigoCarrera()) {
                return repositorioCarreras.replace(codigocarrera, carrera);
            }
        }
        return null;
    }
}
