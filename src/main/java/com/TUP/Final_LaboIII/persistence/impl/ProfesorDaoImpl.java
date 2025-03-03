package com.TUP.Final_LaboIII.persistence.impl;

import com.TUP.Final_LaboIII.model.Profesor;
import com.TUP.Final_LaboIII.persistence.ProfesorDao;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.business.exception.WrongDniException;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProfesorDaoImpl implements ProfesorDao {

    private static Map<Integer, Profesor> repositorioProfesores = new HashMap<>();

    public Profesor newProfesor(Profesor profesor) {
        int nuevoId = repositorioProfesores.isEmpty() ? 1 : Collections.max(repositorioProfesores.keySet()) + 1;
        return repositorioProfesores.put(nuevoId, profesor);
    }

    @Override
    public Profesor loadProfesor(Long dni) {
        for (Profesor profesor: repositorioProfesores.values()) {
            if (dni.equals(profesor.getDni())) {
                return profesor;
            }
        }
        throw new NotFoundException("No se encontro al profesor con ese dni");
    }

    @Override
    public boolean findByDni(Long dni) {
        for (Profesor profesor: repositorioProfesores.values()) {
            if (dni.equals(profesor.getDni())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Profesor deleteProfesor(Long dni) {
        if (!findByDni(dni)) {
            throw new NotFoundException("No se encontro al profesor con ese dni");
        }
        for (Profesor profesor: repositorioProfesores.values()) {
            if (dni.equals(profesor.getDni())) {
                return repositorioProfesores.remove(dni);
            }
        }
        return null;
    }

    @Override
    public Profesor saveProfesor(Profesor profesor) {
        Integer idProfesor = repositorioProfesores.entrySet().stream()
                .filter(entry -> entry.getValue().getDni().equals(profesor.getDni()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No se encontró un profesor con DNI: " + profesor.getDni()));

        repositorioProfesores.put(idProfesor, profesor);

        return profesor;
    }
}
