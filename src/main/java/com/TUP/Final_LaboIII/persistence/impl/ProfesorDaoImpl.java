package com.TUP.Final_LaboIII.persistence.impl;

import com.TUP.Final_LaboIII.model.Profesor;
import com.TUP.Final_LaboIII.persistence.ProfesorDao;
import com.TUP.Final_LaboIII.persistence.exception.NotFoundException;
import com.TUP.Final_LaboIII.persistence.exception.WrongDniException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProfesorDaoImpl implements ProfesorDao {

    private static Map<Long, Profesor> repositorioProfesores = new HashMap<>();

    @Override
    public Profesor newProfesor(Profesor profesor) {
        return repositorioProfesores.put(profesor.getDni(),profesor);
    }

    @Override
    public Profesor loadProfesor(Long dni) {
        for (Profesor profesor: repositorioProfesores.values()) {
            if (dni.equals(profesor.getDni())) {
                return profesor;
            }
        }
        throw new NotFoundException("No se encuentra un profesor con ese dni.");
    }

    @Override
    public boolean findByDni(Long dni) {
        for (Profesor profesor: repositorioProfesores.values()) {
            if (dni.equals(profesor.getDni())) {
                throw new WrongDniException("Hay un problema con el dni: ya existe un profesor con ese dni.");
            }
        }
        return false;
    }

    @Override
    public Profesor deleteProfesor(Long dni) {
        for (Profesor profesor: repositorioProfesores.values()) {
            if (dni.equals(profesor.getDni())) {
                return repositorioProfesores.remove(dni);
            }
        }
        throw new NotFoundException("No se encuentra un profesor con ese dni.");
    }
}
