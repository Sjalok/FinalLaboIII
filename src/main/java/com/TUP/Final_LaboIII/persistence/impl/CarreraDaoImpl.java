package com.TUP.Final_LaboIII.persistence.impl;

import com.TUP.Final_LaboIII.model.Carrera;
import com.TUP.Final_LaboIII.persistence.CarreraDao;
import com.TUP.Final_LaboIII.persistence.exception.NotFoundException;
import com.TUP.Final_LaboIII.persistence.exception.WrongCodeException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CarreraDaoImpl implements CarreraDao {

    private static Map<Integer, Carrera> repositorioCarreras = new HashMap<>();

    @Override
    public Carrera newCarrera(Carrera carrera) {
        return repositorioCarreras.put(carrera.getCodigoCarrera() ,carrera);
    }

    @Override
    public Carrera deleteCarrera(int codigocarrera) {
        for (Carrera carrera: repositorioCarreras.values()) {
            if (codigocarrera == carrera.getCodigoCarrera()) {
                return repositorioCarreras.remove(codigocarrera);
            }
        }
        throw new NotFoundException("La carrera con ese codigo no se encuentra.");
    }

    @Override
    public Carrera loadCarrera(int codigocarrera) {
        for (Carrera carrera: repositorioCarreras.values()) {
            if (codigocarrera == carrera.getCodigoCarrera()) {
                return carrera;
            }
        }
        throw new NotFoundException("La carrera con ese codigo no se encuentra.");
    }

    @Override
    public boolean findByCode(int codigocarrera) {
        for (Carrera carrera: repositorioCarreras.values()) {
            if (codigocarrera == carrera.getCodigoCarrera()) {
                throw new WrongCodeException("Hay un problema con el codigo: ya se encuentra una carrea con ese codigo.");
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
        for (Carrera carreras: repositorioCarreras.values()) {
            if (codigocarrera == carrera.getCodigoCarrera()) {
                return repositorioCarreras.replace(codigocarrera, carrera);
            }
        }
        throw new NotFoundException("La carrera con ese codigo no se encuentra.");
    }
}
