package com.TUP.Final_LaboIII.persistence.impl;

import com.TUP.Final_LaboIII.model.Profesor;
import com.TUP.Final_LaboIII.persistence.ProfesorDao;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.business.exception.WrongDniException;
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
        return null;
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
        for (Profesor profesor: repositorioProfesores.values()) {
            if (dni.equals(profesor.getDni())) {
                return repositorioProfesores.remove(dni);
            }
        }
        return null;
    }
}
