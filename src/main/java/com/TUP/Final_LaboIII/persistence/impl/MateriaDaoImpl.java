package com.TUP.Final_LaboIII.persistence.impl;

import com.TUP.Final_LaboIII.model.Materia;
import com.TUP.Final_LaboIII.persistence.MateriaDao;
import com.TUP.Final_LaboIII.business.exception.NotFoundException;
import com.TUP.Final_LaboIII.business.exception.WrongCodeException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MateriaDaoImpl implements MateriaDao {

    private static Map<Integer, Materia> repositorioMaterias = new HashMap<>();
    @Override
    public Materia loadMateriaId(int idmateria) {
        for (Materia materia: repositorioMaterias.values()) {
            if (idmateria == materia.getMateriaId()) {
                return materia;
            }
        }
        return null;
    }

    @Override
    public Materia loadMateriaNombre(String nombremateria) {
        for (Materia materia: repositorioMaterias.values()) {
            if (nombremateria == materia.getNombre()) {
                return materia;
            }
        }
        return null;
    }

    @Override
    public Materia newMateria(Materia materia) {
        return repositorioMaterias.put(materia.getMateriaId(), materia);
    }

    @Override
    public boolean findByCode(int codigomateria) {
        for (Materia materia: repositorioMaterias.values()) {
            if (codigomateria == materia.getMateriaId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Materia saveMateria(int codigomateria, Materia materia) {
        for (Materia materias: repositorioMaterias.values()) {
            if (codigomateria == materia.getMateriaId()) {
                return repositorioMaterias.replace(codigomateria,materia);
            }
        }
        return null;
    }

    @Override
    public Materia deleteMateria(int codigomateria) {
        for (Materia materia: repositorioMaterias.values()) {
            if (codigomateria == materia.getMateriaId()) {
                return repositorioMaterias.remove(codigomateria);
            }
        }
        return null;
    }
}
